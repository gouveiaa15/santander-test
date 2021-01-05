package com.santandertest.santander.rest.controller;

import com.santandertest.santander.rest.model.MovieSearchResult;
import com.santandertest.santander.rest.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api")
public class MovieController {

  private MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping(value = "/movies/team_search")
  public ResponseEntity<List<MovieSearchResult>> searchMovieByTeamMembers(@RequestParam String member,
                                                 @RequestParam(defaultValue = "false", required = false) boolean convert_ppt) {

    return ResponseEntity.ok().body(movieService.searchMovieByTeamMember(member, convert_ppt));
  }

}
