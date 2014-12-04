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
import uk.co.myfootballclub.model.league.LeagueRanking;

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
        when(restTemplate.exchange("http://www.football-data.org/soccerseasons/100/ranking", HttpMethod.GET, mockRequestHeaders(), League.class
        )).thenReturn(responseEntity);

        assertNotNull("retrieveLeagueStanding() should not return a null value.",
                service.retrieveLeagueStandings(100));

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
        verify(restTemplate, times(1)).exchange("http://www.football-data.org/soccerseasons/354/ranking?matchday=1",
                HttpMethod.GET, mockRequestHeaders(), League.class);

    }

    @Test
    public void assertReturnedLeagueObjectContainsALeagueName() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        assertTrue("League getLeague() has value.",
                leagueObject.getLeague() != null && !leagueObject.getLeague().isEmpty());

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
                leagueObject.getRanking() != null);

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidRank() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to 0.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid rank value.", ranking.getRank() != 0);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidTeam() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to 0.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid rank value.", ranking.getTeam() != null ||
                !ranking.getTeam().isEmpty());
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidCrestURL() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to 0.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid rank value.", ranking.getCrestURL() != null ||
                    !ranking.getCrestURL().isEmpty());
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidPoints() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid points value.", ranking.getPoints() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidGoals() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid Goals value.", ranking.getGoals() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidGoalsAgainst() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid GoalsAgainst value.", ranking.getGoalsAgainst() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectRankingObjectContainsAValidGoalDifference() {

        setDefaultRESTLeagueResult(PREMIER_LEAGUE_ID);
        when(responseEntity.getBody()).thenReturn(setupDefaultLeagueObject());

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);

        List<LeagueRanking> rankingList = leagueObject.getRanking();
        assertTrue("LeagueRanking size() is not equal to -1.", rankingList.size() != 0);

        for(LeagueRanking ranking : rankingList) {
            assertTrue("LeagueRanking object has a valid GoalDifference value.", ranking.getGoalDifference() != -1);
        }

    }

    @Test
    public void assertReturnedLeagueObjectValuesAreEqualToRESTfulServiceJSonResult() {

        League restResult = new League();
        restResult.setLeague("Premier League 2014/2015");
        restResult.setMatchday(13);

        List<LeagueRanking> teamRankingList = new ArrayList<LeagueRanking>();

        LeagueRanking team_one = new LeagueRanking();
        team_one.setTeam("Chelsea FC");
        team_one.setCrestURL("http://upload.wikimedia.org/wikipedia/de/5/5c/Chelsea_crest.svg");
        team_one.setPoints(33);
        team_one.setGoals(30);
        team_one.setGoalsAgainst(11);
        team_one.setGoalDifference(19);

        teamRankingList.add(team_one);

        restResult.setRanking(teamRankingList);

        when(restTemplate.getForObject("http://www.football-data.org/soccerseasons/354/ranking?matchday=1", League.class)).thenReturn(restResult);

        League leagueObject = service.retrieveLeagueStandingsByMatchDay(PREMIER_LEAGUE_ID, 1);
        assertEquals("LeagueObject League Name is Equal to Premier League 2014/2015", restResult.getLeague()
                            ,leagueObject.getLeague());
        assertEquals("LeagueObject MatchDay is Equal to 13", restResult.getMatchday()  ,leagueObject.getMatchday());

        List<LeagueRanking> leagueRankingList = leagueObject.getRanking();

        for(LeagueRanking team : leagueRankingList) {
            assertEquals("LeagueObject Team is Equal to Chelsea FC", team_one.getTeam() , team.getTeam());
            assertEquals("LeagueObject CrestURL is the correct value.", team_one.getCrestURL() , team.getCrestURL());
            assertEquals("LeagueObject Points is equal to 33.", team_one.getPoints() , team.getPoints());
            assertEquals("LeagueObject Goals is equal to 30.", team_one.getGoals() , team.getGoals());
            assertEquals("LeagueObject GoalsAgainst is equal to 11.", team_one.getGoalsAgainst() , team.getGoalsAgainst());
            assertEquals("LeagueObject GoalDifference is equal to 19.", team_one.getGoalDifference() , team.getGoalDifference());

        }

    }


    private void setDefaultRESTLeagueResult(int leagueId) {
        when(restTemplate.exchange("http://www.football-data.org/soccerseasons/" + leagueId + "/ranking?matchday=1", HttpMethod.GET, mockRequestHeaders(),
                League.class)).thenReturn(responseEntity);
    }

    private League setupDefaultLeagueObject() {


        League league = new League();
        league.setLeague("Default League Name");
        league.setMatchday(1);

        List<LeagueRanking> rankingList = new ArrayList<LeagueRanking>();

        LeagueRanking rank = new LeagueRanking();
        rank.setRank(5);
        rank.setTeam("Team Name Here");
        rank.setCrestURL("http://crestUrl.com");
        rank.setPoints(30);
        rank.setGoals(15);
        rank.setGoalsAgainst(8);

        rankingList.add(rank);

        league.setRanking(rankingList);

        return league;
    }

}
