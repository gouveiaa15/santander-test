package com.santandertest.santander.rest.model.moviedb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class MovieDbPersonResult {

  private int page;
  @JsonProperty("results")
  private MovieDbPerson[] moviedbPeople;
  @JsonProperty("total_pages")
  private int totalPages;
  @JsonProperty("total_results")
  private int totalResults;

  public int getPage() { return page; }

  public void setPage(int page) { this.page = page; }

  public MovieDbPerson[] getMoviedbPeople() { return moviedbPeople; }

  public void setMoviedbPeople(MovieDbPerson[] moviedbPeople) { this.moviedbPeople = moviedbPeople; }

  public int getTotalPages() { return totalPages; }

  public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

  public int getTotalResults() { return totalResults; }

  public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

}
