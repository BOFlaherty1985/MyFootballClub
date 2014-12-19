package uk.co.myfootballclub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.service.FixturesByTeamService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Retrieve Fixtures By Days Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class FixturesByDaysControllerTest {

    public static final String RETRIEVE_FIXTURES_BY_DAYS = "/retrieveFixturesByDays";
    private MockMvc mockMvc;

    @InjectMocks
    private FixturesByDaysController fixturesByDaysController;
    @Mock
    private FixturesByTeamService fixtureByTeamService;
    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(fixturesByDaysController)
                .setViewResolvers(viewResolver)
                .build();

    }

    @Test
    public void retrieveFixturesByDays_methodParametersAreValid_statusIsOkay() throws Exception {

        mockMvc.perform(post(RETRIEVE_FIXTURES_BY_DAYS)
                        .param("numberOfDays", "10")
                        .param("typeOfFixture", "n")
                        .param("teamId", "100"))
                .andExpect(status().isOk());

    }

    @Test
    public void retrieveFixturesByDays_fixturesServiceCalled_OnlyOnce() throws Exception {

        mockMvc.perform(post(RETRIEVE_FIXTURES_BY_DAYS)
                        .param("numberOfDays", "10")
                        .param("typeOfFixture", "n")
                        .param("teamId", "100"))
                 .andExpect(status().isOk());

        verify(fixtureByTeamService, times(1)).getFixturesByDays(anyInt(), anyString(), anyInt());

    }

    @Test
    public void retrieveFixturesByDays_methodReturnType_isNotNull() throws Exception {

        when(objectMapper.writeValueAsString(new ArrayList<Fixture>())).thenReturn("jsonStr");

        assertNotNull("retrieveFixturesByDays() return type is null.",
                fixturesByDaysController.retrieveFixturesByDays(anyInt(), anyInt(), anyString()));

    }

    @Test
    public void retrieveFixturesByDays_jacksonJsonMapperIsCalled_once() throws Exception {

        mockMvc.perform(post(RETRIEVE_FIXTURES_BY_DAYS)
                .param("numberOfDays", "10")
                .param("typeOfFixture", "n")
                .param("teamId", "100"))
                .andExpect(status().isOk());


        verify(objectMapper, times(1)).writeValueAsString(new ArrayList<Fixture>());

    }



}
