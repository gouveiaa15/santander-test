package com.santandertest.santander.rest.model;

public class MovieSearchResult {

  private String imdbId;
  private String title;
  private String directorName;
  private String mainActors;
  private String releaseDate;

  public String getImdbId() { return imdbId; }

  public void setImdbId(String imdbId) { this.imdbId = imdbId; }

  public String getTitle() { return title; }

  public void setTitle(String title) { this.title = title; }

  public String getDirectorName() { return directorName; }

  public void setDirectorName(String directorName) { this.directorName = directorName; }

  public String getMainActors() { return mainActors; }

  public void setMainActors(String mainActors) { this.mainActors = mainActors; }

  public String getReleaseDate() { return releaseDate; }

  public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
}
