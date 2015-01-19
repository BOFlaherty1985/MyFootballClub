package uk.co.myfootballclub.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.league.League;
import uk.co.myfootballclub.model.league.LeagueStanding;
import uk.co.myfootballclub.service.impl.LeagueByMatchDayService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Display the league standings by matchday.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={TestConfig.class, WebInitializer.class})
public class LeagueByMatchDayServiceTest extends ServiceTest {

    public static final int PREMIER_LEAGUE_ID = 354;

    @Autowired
    @InjectMocks
    private LeagueByMatchDayService service;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    ResponseEntity<League> responseEntity = new ResponseEntity<League>(HttpStatus.OK);


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void assertThatRetrieveLeagueStandingSsNotNull() {

        when(responseEntity.getBody()).thenReturn(new League());
        when(restTemplate.exchange("http://api.football-data.org/alpha/soccerseasons/100/ranking", HttpMethod.GET, mockRequestHeaders(), League.class
        )).thenReturn(responseEntity);

        assertNotNull("retrieveLeagueStanding() should not return a null value.",
                service.retrieveDataByInt(100));

    }

    @Test
    public void assertThatLeagueByMatchDayServiceDoesNotReturnNullValue() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        assertNotNull("retrieveLeagueStandingByMatchDay() should not return null value.",
                service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1));

    }

    @Test
    public void assertThatRetrieveLeagueStandingByMatchDayReturnsAnInstanceOfLeague() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        assertTrue("retrieveLeagueStandingByMatchDay() returns a League object.", leagueObject instanceof League);

    }

    @Test
    public void verifyRestTemplateGetForObjectMethodHasBeenCalled() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(new League());

        service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        verify(restTemplate, times(1)).exchange("http://api.football-data.org/alpha/soccerseasons/354/ranking?matchday=1",
                HttpMethod.GET, mockRequestHeaders(), League.class);

    }

    @Test
    public void assertReturnedLeagueObjectContainsALeagueName() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        assertTrue("League getLeagueCaption() has value.",
                leagueObject.getLeagueCaption() != null && !leagueObject.getLeagueCaption().isEmpty());

    }

    @Test
    public void assertReturnedLeagueObjectContainsARanking() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        assertTrue("League getMatchday() has a value that isn't zero.",
                leagueObject.getMatchday() != 0);

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectIsNotNull() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        assertTrue("League has an instance of LeagueRanking and LeagueRanking is not null.",
                leagueObject.getStanding() != null);

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidRank() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueStanding> rankingList = leagueObject.getStanding();
        assertTrue("LeagueRanking size() is not equal to 0.", rankingList.size() != 0);

        for(LeagueStanding ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid rank value.", ranking.getPosition() != 0);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidTeam() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueStanding> rankingList = leagueObject.getStanding();
        assertTrue("LeagueRanking size() is not equal to 0.", rankingList.size() != 0);

        for(LeagueStanding ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid rank value.", ranking.getTeamName() != null ||
                !ranking.getTeamName().isEmpty());
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidPoints() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueStanding> rankingList = leagueObject.getStanding();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueStanding ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid points value.", ranking.getPoints() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidGoals() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueStanding> rankingList = leagueObject.getStanding();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueStanding ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid Goals value.", ranking.getGoals() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidGoalsAgainst() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueStanding> rankingList = leagueObject.getStanding();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueStanding ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid GoalsAgainst value.", ranking.getGoalsAgainst() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidGoalDifference() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueStanding> rankingList = leagueObject.getStanding();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueStanding ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid GoalDifference value.", ranking.getGoalDifference() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectValuesAreEqualToRESTfulServiceJSonResult() {

        League restResult = new League();
        restResult.setLeagueCaption("Premier League 2014/2015");
        restResult.setMatchday(13);

        List<LeagueStanding> teamRankingList = new ArrayList<LeagueStanding>();

        LeagueStanding team_one = new LeagueStanding();
        team_one.setTeamName("Chelsea FC");
        team_one.setPoints(33);
        team_one.setGoals(30);
        team_one.setGoalsAgainst(11);
        team_one.setGoalDifference(19);

        teamRankingList.add(team_one);

        restResult.setStanding(teamRankingList);

        when(restTemplate.exchange("http://api.football-data.org/alpha/soccerseasons/354/ranking?matchday=1",
                HttpMethod.GET, mockRequestHeaders(), League.class)).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(restResult);

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        assertEquals("LeagueObject League Name is Equal to Premier League 2014/2015", restResult.getLeagueCaption()
                            ,leagueObject.getLeagueCaption());
        assertEquals("LeagueObject MatchDay is Equal to 13", restResult.getMatchday()  ,leagueObject.getMatchday());

        List<LeagueStanding> leagueRankingList = leagueObject.getStanding();

        for(LeagueStanding team : leagueRankingList) {
            assertEquals("LeagueObject Team is Equal to Chelsea FC", team_one.getTeamName() , team.getTeamName());
            assertEquals("LeagueObject Points is equal to 33.", team_one.getPoints() , team.getPoints());
            assertEquals("LeagueObject Goals is equal to 30.", team_one.getGoals() , team.getGoals());
            assertEquals("LeagueObject GoalsAgainst is equal to 11.", team_one.getGoalsAgainst() , team.getGoalsAgainst());
            assertEquals("LeagueObject GoalDifference is equal to 19.", team_one.getGoalDifference() , team.getGoalDifference());

        }

    }

    private void setDefaultRESTLeagueResult(int leagueId) {
        when(restTemplate.exchange("http://api.football-data.org/alpha/soccerseasons/" + leagueId + "/ranking?matchday=1", HttpMethod.GET, mockRequestHeaders(),
                League.class)).thenReturn(responseEntity);
    }

    private League setupDefaultLeagueObject() {


        League league = new League();
        league.setLeagueCaption("Default League Name");
        league.setMatchday(1);

        List<LeagueStanding> rankingList = new ArrayList<LeagueStanding>();

        LeagueStanding rank = new LeagueStanding();
        rank.setPosition(5);
        rank.setTeamName("Team Name Here");
        rank.setPoints(30);
        rank.setGoals(15);
        rank.setGoalsAgainst(8);

        rankingList.add(rank);

        league.setStanding(rankingList);

        return league;
    }

}
