package com.santandertest.santander.rest.external;

import com.santandertest.santander.rest.model.MovieSearchResult;
import com.santandertest.santander.rest.model.omdb.OmdbMovie;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OmdbApiClient {

  private String movieEndpoint;
  private RestTemplate restTemplate;
  private HttpHeaders baseHttpHeader;

  @Autowired
  public OmdbApiClient(@Value("${external-api.omdb.api-key}") String apiKey,
                       @Value("${external-api.omdb.movie-endpoint}") String movieEndpoint) {
    this.movieEndpoint = movieEndpoint + apiKey;
    this.restTemplate = new RestTemplate();
  }

  @PostConstruct
  public void init(){
    baseHttpHeader = buildBaseHttpHeader();
  }

  public MovieSearchResult searchMovieInfo(String movieId) {
    ResponseEntity<OmdbMovie> movieInfo = restTemplate.exchange(movieEndpoint+"&i="+movieId, HttpMethod.GET,
                                                      new HttpEntity<HttpHeaders>(baseHttpHeader), OmdbMovie.class);
    MovieSearchResult movieSearchResult = new MovieSearchResult();

    if(movieInfo.getBody() != null) {
      movieSearchResult.setTitle(movieInfo.getBody().getTitle());
      movieSearchResult.setDirectorName(movieInfo.getBody().getDirector());
      movieSearchResult.setMainActors(movieInfo.getBody().getActors());
    }

    return movieSearchResult;
  }

  private HttpHeaders buildBaseHttpHeader() {
    HttpHeaders httpHeader = new HttpHeaders();
    httpHeader.setContentType(MediaType.APPLICATION_JSON);

    return httpHeader;
  }

}
