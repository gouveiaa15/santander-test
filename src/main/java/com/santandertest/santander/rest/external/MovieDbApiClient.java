package com.santandertest.santander.rest.external;

import com.santandertest.santander.rest.model.moviedb.MovieDbMovie;
import com.santandertest.santander.rest.model.moviedb.MovieDbPersonResult;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieDbApiClient {

  private String personEndpoint;
  private String movieEndpoint;
  private RestTemplate restTemplate;
  private HttpHeaders baseHttpHeader;
  private static final Logger log = LoggerFactory.getLogger(MovieDbApiClient.class);

  @Autowired
  public MovieDbApiClient(@Value("${external-api.movie-db.api-key}") String apiKey,
                          @Value("${external-api.movie-db.person-endpoint}") String personEndpoint,
                          @Value("${external-api.movie-db.movie-endpoint}") String movieEndpoint) {

    this.personEndpoint = personEndpoint + apiKey;
    this.movieEndpoint = movieEndpoint + apiKey;
    this.restTemplate = new RestTemplate();
  }

  @PostConstruct
  public void init(){
    baseHttpHeader = buildBaseHttpHeader();
  }

  public Map<String, String> getMovieIdAndDate(String memberName) {
    Set<Long> movieDbIds = getMovieDbIds(memberName);

    return getMovieDbInfo(movieDbIds);
  }

  private Set<Long> getMovieDbIds(String memberName) {
    Set<Long> movieDbIds = new HashSet<>();
    try {
      int currentPage = 1;
      int totalPages = 2;

      while (totalPages > currentPage) {
        ResponseEntity<MovieDbPersonResult> person = restTemplate.exchange(
                                                    buildPersonUri(memberName,String.valueOf(currentPage)), HttpMethod.GET,
                                                    new HttpEntity<HttpHeaders>(baseHttpHeader), MovieDbPersonResult.class);
        if(person.getBody() != null) {
          totalPages = person.getBody().getTotalPages();
          for(int i = 0; i < person.getBody().getMoviedbPeople().length; i++){
            for(int j = 0; j < person.getBody().getMoviedbPeople()[i].getKnownFor().length; j++) {
              movieDbIds.add(person.getBody().getMoviedbPeople()[i].getKnownFor()[j].getId());
            }
          }
        }
        currentPage++;
      }
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error(String.format("Failed to fetch person info with name %s", memberName), e);
    }

    return movieDbIds;
  }

  private Map<String, String> getMovieDbInfo(Set<Long> movieDbIds) {
    Map<String, String> movieImdbIdsWithRelease = new HashMap<>();
    try {
      for(Long id: movieDbIds) {
        String uri = movieEndpoint.replace("{movieId}", String.valueOf(id));
        ResponseEntity<MovieDbMovie> movieInfo = restTemplate.exchange(uri, HttpMethod.GET,
                                                  new HttpEntity<HttpHeaders>(baseHttpHeader), MovieDbMovie.class);
        if(movieInfo.getBody() != null) {
          movieImdbIdsWithRelease.put(movieInfo.getBody().getImbdId(), movieInfo.getBody().getReleasedDate());
        }
      }
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("Failed to fetch movie info with", e);
    }

    return movieImdbIdsWithRelease;
  }

  private String buildPersonUri(String query, String currentPage) {
    StringBuilder uriBuilder = new StringBuilder();
    uriBuilder.append(personEndpoint);
    uriBuilder.append("&query=");
    uriBuilder.append(query);
    uriBuilder.append("&page=");
    uriBuilder.append(currentPage);

    return uriBuilder.toString();
  }

  private HttpHeaders buildBaseHttpHeader() {
    HttpHeaders httpHeader = new HttpHeaders();
    httpHeader.setContentType(MediaType.APPLICATION_JSON);

    return httpHeader;
  }

}