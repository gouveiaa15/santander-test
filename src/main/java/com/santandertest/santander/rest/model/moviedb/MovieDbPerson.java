package com.santandertest.santander.rest.model.moviedb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class MovieDbPerson {

  @JsonProperty("known_for")
  private KnownFor[] knownFor;


  public KnownFor[] getKnownFor() { return knownFor; }

  public void setKnownFor(KnownFor[] knownFor) { this.knownFor = knownFor; }

}
