package com.santandertest.santander.rest.service;

import com.santandertest.santander.rest.model.MovieSearchResult;
import java.util.List;

public interface MovieService {

  List<MovieSearchResult> searchMovieByTeamMember(String memberName, boolean convertToPpt);
  void convertResultToPpt(String fileName, List<MovieSearchResult> movieSearchResult);

}
