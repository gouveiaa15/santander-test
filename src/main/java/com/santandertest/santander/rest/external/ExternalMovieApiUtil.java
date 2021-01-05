package com.santandertest.santander.rest.external;

import com.santandertest.santander.rest.model.MovieSearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalMovieApiUtil {

  private MovieDbApiClient movieDbApiClient;
  private OmdbApiClient omdbApiClient;

  /*
   * An improvement here could be replacing this
   * components autowired with a factory with the proper abstraction
   * and also giving the possibility for the client to choose
   * which sources to search.
   */
  @Autowired
  public ExternalMovieApiUtil(MovieDbApiClient movieDbApiClient, OmdbApiClient omdbApiClient) {
    this.movieDbApiClient = movieDbApiClient;
    this.omdbApiClient = omdbApiClient;
  }

  public List<MovieSearchResult> searchTeamMemberMovies(String memberName) {
    Map<String, String> moviesImdbIdWithRelease =  movieDbApiClient.getMovieIdAndDate(memberName);
    List<MovieSearchResult> searchResults = new ArrayList<>();

    for (Map.Entry<String, String> entry : moviesImdbIdWithRelease.entrySet()) {
      if(entry.getKey() != null && !entry.getKey().isEmpty()) {
        MovieSearchResult movieSearchResult = omdbApiClient.searchMovieInfo(entry.getKey());
        movieSearchResult.setImdbId(entry.getKey());
        movieSearchResult.setReleaseDate(entry.getValue());
        searchResults.add(movieSearchResult);
      }
    }

    return searchResults;
  }

}
