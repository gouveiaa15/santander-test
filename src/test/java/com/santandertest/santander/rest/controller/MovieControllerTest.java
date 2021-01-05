package com.santandertest.santander.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieControllerTest {

  private MockMvc mockMvc;

  @MockBean
  private MovieController movieController;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
  }

  @Test
  public void withValidMemberParamShouldReturnOkTest() throws Exception {

    mockMvc.perform(get("/api/movies/team_search")
                    .param("member", "martin scorses"))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn().getResponse();
  }

  @Test
  public void withInvalidQueryParamShouldReturnClientErrorTest() throws Exception {

    mockMvc.perform(get("/api/movies/team_search")
                    .param("membe", "martin scorses"))
                    .andExpect(status().is4xxClientError())
                    .andReturn().getResponse();
  }

  @Test
  public void withNoQueryParamShouldReturnClientErrorTest() throws Exception {

    mockMvc.perform(get("/api/movies/team_search"))
                    .andExpect(status().is4xxClientError())
                    .andReturn().getResponse();
  }

  @Test
  public void withValidRequiredParamAndInvalidOptionalParamShouldReturnClientErrorTest() throws Exception {

    mockMvc.perform(get("/api/movies/team_search")
                    .param("member", "martin scorses")
                    .param("convert_ppt","t"))
                    .andExpect(status().is4xxClientError())
                    .andReturn().getResponse();
  }

  @Test
  public void withValidRequiredParamAndValidOptionalParamShouldReturnOkTest() throws Exception {

    mockMvc.perform(get("/api/movies/team_search")
                    .param("member", "martin scorses")
                    .param("convert_ppt","true"))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn().getResponse();
  }

}
