package com.santandertest.santander.rest.service;

import com.santandertest.santander.rest.converter.PptConverter;
import com.santandertest.santander.rest.external.ExternalMovieApiUtil;
import com.santandertest.santander.rest.model.MovieSearchResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMovieService implements MovieService{

  private ExternalMovieApiUtil externalMovieApiUtil;
  private PptConverter pptConverter;

  @Autowired
  public DefaultMovieService(ExternalMovieApiUtil externalMovieApiUtil,
                            PptConverter pptConverter) {
    this.externalMovieApiUtil = externalMovieApiUtil;
    this.pptConverter = pptConverter;
  }

  @Override
  public List<MovieSearchResult> searchMovieByTeamMember(String memberName, boolean convertToPpt) {
    List<MovieSearchResult> movieSearchResults = externalMovieApiUtil.searchTeamMemberMovies(memberName);
    if (convertToPpt && !movieSearchResults.isEmpty())
      convertResultToPpt(memberName, movieSearchResults);

    return movieSearchResults;
  }

  @Override
  public void convertResultToPpt(String fileName, List<MovieSearchResult> movieSearchResult) {
    pptConverter.generatePpt(fileName, movieSearchResult);
  }

}
