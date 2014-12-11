package uk.co.myfootballclub.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.ClubDetails;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.league.League;
import uk.co.myfootballclub.model.weather.WeatherFixture;
import uk.co.myfootballclub.service.ClubDetailsService;
import uk.co.myfootballclub.service.FixturesByTeamService;
import uk.co.myfootballclub.service.LeagueByMatchDayService;
import uk.co.myfootballclub.service.WeatherForecastForFixtureService;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * My Football Team Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/11/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class MyFootballTeamControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private MyFootballTeamController controller;

    @Mock
    private FixturesByTeamService fixturesByDaysService;
    @Mock
    private LeagueByMatchDayService leagueByMatchDayService;
    @Mock
    private WeatherForecastForFixtureService weatherForecastForFixtureService;
    @Mock
    private ClubDetailsService clubDetailsService;

    @Mock
    Fixture nextFixture;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();

    }

    @Test
    public void assertThatInvalidRequestMappingResultsInErrorStatus404() throws Exception {

        mockMvc.perform(get("/myFootballTeams"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void verifyMyFootballTeamControllerDefaultRequestMappingIsValidAndReturnsStatus200() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());

    }

    @Test
    public void verifyMyFootballTeamControllerDefaultMappingReturnsViewNameDisplayFootballTeam() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"));

    }

    @Test
    public void verifyMyFootballTeamControllerPageDisplayContainsTeamModelObject() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"))
                .andExpect(model().attributeExists("team"));

    }

    @Test
    public void verifyMyFootballTeamControllerPageDisplayContainsFixtureModelObject() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"))
                .andExpect(model().attributeExists("upcomingFixtures"));

    }

    @Test
    public void verifyMyFootballTeamControllerPageDisplayContainsResultsModelObject() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"))
                .andExpect(model().attributeExists("recentResults"));

    }

    @Test
    public void verifyMyFootballTeamControllerPageDisplayContainsLeagueModelObject() throws Exception {

        when(leagueByMatchDayService.retrieveLeagueStandings(354)).thenReturn(new League());

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"))
                .andExpect(model().attributeExists("leagueStandings"));

    }

    @Test
    public void verifyFixturesByDayServiceHasBeenCalledOneTime() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());

        verify(fixturesByDaysService, atLeastOnce()).getFixturesByDays(anyInt(), Matchers.<String> any(), anyInt());

    }

    @Test
    public void verifyLeagueByMatchDayServiceHasBeenCalledAtLeastOnce() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());

        verify(leagueByMatchDayService, atLeastOnce()).retrieveLeagueStandings(anyInt());

    }

    @Test
    public void verifyWeatherForecastForFixtureServiceHasBeenCalledOnetime() throws Exception {

        when(fixturesByDaysService.getTeamsNextFixture(anyInt())).thenReturn(nextFixture);

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());
        verify(weatherForecastForFixtureService, atLeastOnce()).retrieveWeatherForecastForFixture(nextFixture);

    }

    @Test
    public void verifyMyFootballTeamControllerPageDisplayContainsWeatherForFixtureModelObject() throws Exception {

        when(fixturesByDaysService.getTeamsNextFixture(anyInt())).thenReturn(nextFixture);
        when(weatherForecastForFixtureService.retrieveWeatherForecastForFixture(nextFixture))
                .thenReturn(new WeatherFixture());

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"))
                .andExpect(model().attributeExists("weatherForFixture"));
    }

    @Test
    public void verifyMyFootballTeamControllerDisplaysTeamsNextFixtureHasBeenCalledForTeam563() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());

        verify(fixturesByDaysService, times(1)).getTeamsNextFixture(anyInt());

    }

    @Test
    public void assertThatMyFootballTeamControllerHasModelObjectOfTeamsNextFixture() throws Exception {

        when(fixturesByDaysService.getTeamsNextFixture(anyInt())).thenReturn(new Fixture());

        mockMvc.perform(get("/myFootballTeam")).andExpect(status().isOk())
                .andExpect(model().attributeExists("teamsNextFixture"));

    }

    @Test
    public void verifyMyFootballTeamControllerClubDetailsServiceHasBeenCalledForAGivenTeam() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());

        verify(clubDetailsService, times(1)).retrieveClubDetails(anyString());

    }

    @Test
    public void assertThatMyFootballTeamControllerHasModelObjectOfClubDetails() throws Exception {

        when(clubDetailsService.retrieveClubDetails(anyString())).thenReturn(new ClubDetails());

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
        .andExpect(model().attributeExists("clubDetails"));

    }


}