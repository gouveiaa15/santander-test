package com.santandertest.santander.rest.model.moviedb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class MovieDbMovie {

  @JsonProperty("imdb_id")
  private String imbdId;
  @JsonProperty("original_title")
  private String originalTitle;
  @JsonProperty("release_date")
  private String releasedDate;


  public String getImbdId() { return imbdId; }

  public void setImbdId(String imbdId) { this.imbdId = imbdId; }

  public String getOriginalTitle() { return originalTitle; }

  public void setOriginalTitle(String originalTitle) { this.originalTitle = originalTitle; }

  public String getReleasedDate() { return releasedDate; }

  public void setReleasedDate(String releasedDate) { this.releasedDate = releasedDate; }
}
