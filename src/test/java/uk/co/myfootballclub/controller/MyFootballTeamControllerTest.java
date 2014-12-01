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
import uk.co.myfootballclub.service.FixturesByDayService;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Description Here
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
    private FixturesByDayService fixturesByDaysService;

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

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFootballTeam"))
                .andExpect(model().attributeExists("league"));


    }

    @Test
    public void verifyFixturesByDayServiceHasBeenCalledOneTime() throws Exception {

        mockMvc.perform(get("/myFootballTeam"))
                .andExpect(status().isOk());

        verify(fixturesByDaysService, atLeastOnce()).getFixturesByDays(anyInt(), Matchers.<String> any(), anyInt());

    }


}
