package uk.co.myfootballclub.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.persistence.dao.TeamListRepository;
import uk.co.myfootballclub.persistence.domain.TeamList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Service to retrieve teams for drop down menu
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class TeamsDropdownServiceTest {

    private static final int LEAGUE_ID_TESTCASE = 1;
    private static final int TEAM_ID_TESTCASE = 1;

    @InjectMocks
    private TeamsDropdownService teamsDropdownService;
    @Mock
    private TeamListRepository teamListRepository;

    private Sort sortByTeamDescription = new Sort(Sort.Direction.ASC, "teamDescription");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveListOfTeams_returnsNull_assertionFailureMustNotReturnNull() {

        assertNotNull("retrieveListOfTeams() return must not be null",
                teamsDropdownService.retrieveListOfTeams(1));
    }

    @Test
    public void retrieveListOfTeams_returnType_listOfTeamListObjectsReturned() {

        when(teamListRepository.findByLeagueId(1, sortByTeamDescription)).thenReturn(mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, TEAM_ID_TESTCASE));

        assertTrue("retrieveListOfTeams() returns List<TeamList>", teamsDropdownService.retrieveListOfTeams(1)
                .get(0) instanceof TeamList);

    }

    @Test
    public void retrieveListOfTeams_teamListReturnSize_isGreaterThanZero() {

        when(teamListRepository.findByLeagueId(1, sortByTeamDescription)).thenReturn(mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, TEAM_ID_TESTCASE));

        List<TeamList> teamsList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        assertTrue("teamsList size is greater than zero.", teamsList.size() > 0);
    }

    @Test
    public void retrieveListOfTeams_teamListHasTeamId_teamIdIsEqualTo1() {

        when(teamListRepository.findByLeagueId(LEAGUE_ID_TESTCASE, sortByTeamDescription)).thenReturn(mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, TEAM_ID_TESTCASE));

        List<TeamList> teamsList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        TeamList testObject = teamsList.get(0);

        assertEquals("teamList object teamId value is equal to 1", TEAM_ID_TESTCASE, testObject.getTeamId());

    }

    @Test
    public void retrieveListOfTeams_teamListHasTeamDescription_teamDescriptionHasValue() {

        when(teamListRepository.findByLeagueId(1, sortByTeamDescription)).thenReturn(mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, TEAM_ID_TESTCASE));

        List<TeamList> teamsList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        TeamList testObject = teamsList.get(0);

        assertTrue("teamList object teamDescription has value.", !testObject.getTeamDescription().isEmpty());

    }

    @Test
    public void retrieveListOfTeams_teamListHasLeagueId_leagueIdIsEqualTo1() {

        when(teamListRepository.findByLeagueId(LEAGUE_ID_TESTCASE, sortByTeamDescription)).thenReturn(mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, TEAM_ID_TESTCASE));

        List<TeamList> teamsList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        TeamList testObject = teamsList.get(0);

        assertEquals("teamList object leagueId value is equal to 1", LEAGUE_ID_TESTCASE, testObject.getLeagueId());
    }

    @Test
    public void retrieveListOfTeams_leagueIdEqualToMethodInputParameter_leagueIdIsEqualToInput() {

        int leagueId = 5;

        when(teamListRepository.findByLeagueId(leagueId, sortByTeamDescription)).thenReturn(mockPremierLeagueTeamsList(leagueId, TEAM_ID_TESTCASE));

        List<TeamList> teamsList = teamsDropdownService.retrieveListOfTeams(leagueId);
        TeamList testObject = teamsList.get(0);

        assertEquals("teamList object leagueId value is equal to 5", leagueId, testObject.getLeagueId());

    }

    @Test
    public void retrieveListOfTeams_teamListRepositoryIsCalled_verifyRepositoryIsCalledOnce() {

        final int LEAGUE_ID = 2;

        teamsDropdownService.retrieveListOfTeams(LEAGUE_ID);
        verify(teamListRepository, times(1)).findByLeagueId(LEAGUE_ID, sortByTeamDescription);

    }

    @Test
    public void retrieveListOfTeams_premierLeagueTeamsListReturnedFromRepository_verifyMethodReturnSizeIsCorrect() {

        List<TeamList> premierLeagueTeams = mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, 30);

        when(teamListRepository.findByLeagueId(LEAGUE_ID_TESTCASE, sortByTeamDescription)).thenReturn(premierLeagueTeams);

        List<TeamList> resultList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        assertEquals("List<TeamList> result is equal to given size mocked league list", premierLeagueTeams.size(),
                resultList.size());


    }

    @Test
    public void retrieveListOfTeams_premierLeagueTeamsListReturnedFromRepository_assertEqualsObjectIsEqualToInputOne() {

        List<TeamList> premierLeagueTeams = mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, 30);

        when(teamListRepository.findByLeagueId(LEAGUE_ID_TESTCASE, sortByTeamDescription)).thenReturn(premierLeagueTeams);

        List<TeamList> resultList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        assertEquals(premierLeagueTeams.get(0), resultList.get(0));

    }

    @Test
    public void retrieveListOfTeams_premierLeagueTeamsListReturnedFromRepository_assertEqualsObjectIsEqualToInputTwo() {

        List<TeamList> premierLeagueTeams = mockPremierLeagueTeamsList(LEAGUE_ID_TESTCASE, 30);

        when(teamListRepository.findByLeagueId(LEAGUE_ID_TESTCASE, sortByTeamDescription)).thenReturn(premierLeagueTeams);

        List<TeamList> resultList = teamsDropdownService.retrieveListOfTeams(LEAGUE_ID_TESTCASE);
        assertEquals(premierLeagueTeams.get(1), resultList.get(1));

    }


    private List<TeamList> mockPremierLeagueTeamsList(int leagueId, int teamId) {

        TeamList club_one = new TeamList();
        club_one.setLeagueId(leagueId);
        club_one.setTeamDescription("teamOne");
        club_one.setTeamId(teamId);

        TeamList club_two = new TeamList();
        club_two.setLeagueId(leagueId);
        club_two.setTeamDescription("teamTwo");
        club_two.setTeamId(teamId);

        List<TeamList> teamsList = new ArrayList<TeamList>();
        teamsList.add(club_one);
        teamsList.add(club_two);

        return teamsList;
    }


}
