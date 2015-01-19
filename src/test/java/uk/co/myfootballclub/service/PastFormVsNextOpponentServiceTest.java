package uk.co.myfootballclub.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.service.impl.PastFormVsNextOpponentService;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Service to retrieve past form (results) vs. chosen teams next opponent.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class PastFormVsNextOpponentServiceTest extends ServiceTest {

    @InjectMocks
    private PastFormVsNextOpponentService pastFormService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<Fixture[]> response = new ResponseEntity<Fixture[]>(HttpStatus.OK);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private int teamId = 10;

    @Test
    public void retrievePastFormAgainstNextOpponent_returnType_isNotNull() {
        assertNotNull("Method return type must not be null.", pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent"));
    }

    @Test
    public void retrievePastFormAgainstNextOpponent_returnType_isAListOfFixtures() {

        List<Fixture> result = pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

        for(Fixture f : result) {
            assertTrue("List contains objects of type Fixture", f instanceof Fixture);
        }

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_returnType_hasSizeGreaterThanZero() {

        List<Fixture> result = pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

        assertTrue("List size is greater than Zero.", result.size() > 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void retrievePastFormAgainstNextOpponent_teamIdInputParameter_mustNotEqualZero() {

        pastFormService.retrievePastFormAgainstNextOpponent(0, "nextOpponent");

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_teamIdInputParameterException_errorMessageIsValid() {

        try {
            pastFormService.retrievePastFormAgainstNextOpponent(0, "nextOpponent");
        } catch(Exception e) {
            assertEquals("Error message is valid", "TeamId must not be equal to 0.", e.getMessage());
        }

    }

    @Test(expected = NullPointerException.class)
    public void retrievePastFormAgainstNextOpponent_nextOpponentInputParameter_mustNotBeNull() {

        pastFormService.retrievePastFormAgainstNextOpponent(teamId, null);

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_nextOpponentInputParameter_errorMessageIsValid() {

        try {
            pastFormService.retrievePastFormAgainstNextOpponent(teamId, null);
        } catch(Exception e) {
            assertEquals("nextOpponent null error message is valid", "NextOpponent must not be null.", e.getMessage());
        }

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_restTemplateCalled_threeTimesOnceForTheLastThreeYears() {

        String rest_url_2014 = "http://api.football-data.org/alpha/alpha/teams/" + teamId + "/fixtures?season=2014";
        String rest_url_2013 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2013";
        String rest_url_2012 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2012";

        mockResponseBody(mockFixturesArray());

        mockRestTemplateExecution(rest_url_2014);
        mockRestTemplateExecution(rest_url_2013);
        mockRestTemplateExecution(rest_url_2012);

       pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

       verify(restTemplate, times(3)).exchange(any(String.class),
               any(HttpMethod.class), any(HttpEntity.class), eq(Fixture[].class));

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_restTemplateCall_forFixturesYear2014() {

        teamId = 563;

        String rest_url_2014 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2014";
        String rest_url_2013 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2013";
        String rest_url_2012 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2012";

        mockResponseBody(mockFixturesArray());

        mockRestTemplateExecution(rest_url_2014);
        mockRestTemplateExecution(rest_url_2013);
        mockRestTemplateExecution(rest_url_2012);

        pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

        verify(restTemplate, times(1)).exchange(rest_url_2014, HttpMethod.GET, mockRequestHeaders(), Fixture[].class);

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_restTemplateCall_forFixturesYear2013() {

        teamId = 563;

        String rest_url_2014 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2014";
        String rest_url_2013 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2013";
        String rest_url_2012 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2012";

        mockResponseBody(mockFixturesArray());

        mockRestTemplateExecution(rest_url_2014);
        mockRestTemplateExecution(rest_url_2013);
        mockRestTemplateExecution(rest_url_2012);

        pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

        verify(restTemplate, times(1)).exchange(rest_url_2013, HttpMethod.GET, mockRequestHeaders(), Fixture[].class);

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_restTemplateCall_forFixturesYear2012() {

        teamId = 563;

        String rest_url_2014 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2014";
        String rest_url_2013 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2013";
        String rest_url_2012 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2015";

        mockResponseBody(mockFixturesArray());

        mockRestTemplateExecution(rest_url_2014);
        mockRestTemplateExecution(rest_url_2013);
        mockRestTemplateExecution(rest_url_2012);

        pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

        verify(restTemplate, times(1)).exchange(rest_url_2012, HttpMethod.GET, mockRequestHeaders(), Fixture[].class);

    }

    @Test
    public void retrievePastFormAgainstNextOpponent_restTemplateCallTeamId_isEqualToInputParameter() {

        String rest_url_2014 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2014";
        String rest_url_2013 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2013";
        String rest_url_2012 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2015";

        mockResponseBody(mockFixturesArray());

        mockRestTemplateExecution(rest_url_2014);
        mockRestTemplateExecution(rest_url_2013);
        mockRestTemplateExecution(rest_url_2012);

        pastFormService.retrievePastFormAgainstNextOpponent(teamId, "nextOpponent");

        verify(restTemplate, times(1)).exchange(rest_url_2014, HttpMethod.GET, mockRequestHeaders(), Fixture[].class);
        verify(restTemplate, times(1)).exchange(rest_url_2013, HttpMethod.GET, mockRequestHeaders(), Fixture[].class);
        verify(restTemplate, times(1)).exchange(rest_url_2012, HttpMethod.GET, mockRequestHeaders(), Fixture[].class);
    }

    @Test
    public void retrievePastFormAgainstNextOpponent_returnedFixturesListSize_isEqualToRestTemplateResult() {

        String rest_url_2014 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2014";
        String rest_url_2013 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2013";
        String rest_url_2012 = "http://api.football-data.org/alpha/teams/" + teamId + "/fixtures?season=2012";

        mockResponseBody(mockFixturesArray());

        mockRestTemplateExecution(rest_url_2014);
        mockRestTemplateExecution(rest_url_2013);
        mockRestTemplateExecution(rest_url_2012);

        List<Fixture> result = pastFormService.retrievePastFormAgainstNextOpponent(teamId, "HomeTeam1");
        assertEquals("Fixture List size is equal to 2.", 2, result.size());

    }

    private void mockRestTemplateExecution(String rest_url_2014) {
        when(restTemplate.exchange(rest_url_2014, HttpMethod.GET, mockRequestHeaders(), Fixture[].class)).thenReturn(response);
    }

    private void mockResponseBody(Fixture[] fixturesArray) {
        when(response.getBody()).thenReturn(fixturesArray);
    }

    private Fixture[] mockFixturesArray() {

        Fixture[] fixtures = new Fixture[2];

        Fixture fixture_one = new Fixture();
        fixture_one.setHomeTeam("HomeTeam1");
        fixture_one.setAwayTeam("AwayTeam1");

        fixtures[0] = fixture_one;

        Fixture fixture_two = new Fixture();
        fixture_two.setHomeTeam("HomeTeam2");
        fixture_two.setAwayTeam("AwayTeam2");

        fixtures[1] = fixture_two;

        return fixtures;
    }


}
