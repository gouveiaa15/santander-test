package com.santandertest.santander.rest.model.omdb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class OmdbMovie {

  @JsonProperty("Title")
  private String title;
  @JsonProperty("Director")
  private String director;
  @JsonProperty("Actors")
  private String actors;

  public String getTitle() { return title; }

  public void setTitle(String title) { this.title = title; }

  public String getDirector() { return director; }

  public void setDirector(String director) { this.director = director; }

  public String getActors() { return actors; }

  public void setActors(String actors) { this.actors = actors; }
}
