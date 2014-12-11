package uk.co.myfootballclub.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.Team;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static java.lang.String.format;

/**
 * Retrieves a football club from football-data.org
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class ClubServiceTest extends ServiceTest {

    @InjectMocks
    private ClubService clubService;
    private int teamId;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    ResponseEntity<Team> responseEntity = new ResponseEntity<Team>(HttpStatus.OK);

    private static final String RESTFUL_URL = "http://www.football-data.org/teams/";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveFootballClub_nullReturn_failMustNotReturnNull() {

        teamId = 50;

        Team aFootballTeam = mockTeamObject(null, null);

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        Object result = clubService.retrieveFootballClubById(teamId);
        assertNotNull("retrieveFootballClubById() must not return null.", result);

    }

    @Test
    public void retrieveFootballClub_returnValue_instanceOfTeam() {

        teamId = 60;

        Team aFootballTeam = mockTeamObject(null, null);

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        Object result = clubService.retrieveFootballClubById(teamId);
        assertTrue("Returned object is instanceOf Team", result instanceof Team);

    }

    @Test
    public void retrieveFootballClub_teamClubNameIsNull_failClubNameMustNotBeNull() {

        teamId = 70;

        Team aFootballTeam = mockTeamObject("name", null);

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        Team result = clubService.retrieveFootballClubById(teamId);
        assertNotEquals("Result clubName must not be null.", null, result.getName());

    }

    @Test
    public void retrieveFootballClub_teamClubCrestIsNull_failClubCrestMustNotBeNull() {

        teamId = 80;

        Team aFootballTeam = mockTeamObject(null, "crestUrl");

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        Team result = clubService.retrieveFootballClubById(teamId);
        assertNotEquals("Result clubCrest must not be null.", null, result.getCrestUrl());

    }

    @Test
    public void retrieveFootballClub_restTemplateInstance_calledOnce() {

        teamId = 10;

        Team aFootballTeam = mockTeamObject(null, null);

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        clubService.retrieveFootballClubById(teamId);
        verify(restTemplate, times(1)).exchange(format("%s%s", RESTFUL_URL, teamId)
                , HttpMethod.GET, mockRequestHeaders(), Team.class);

    }

    @Test
    public void retrieveFootballClub_resultTeamNameValueIsEqualTo_WestHamUnited() {

        teamId = 10;

        Team aFootballTeam = mockTeamObject("West Ham United", null);

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        Team result = clubService.retrieveFootballClubById(teamId);
        assertEquals(aFootballTeam.getName(), result.getName());

    }

    @Test
    public void retrieveFootballClub_resultTeamCrestUrlValueIsEqualTo_validCrestURL() {

        teamId = 50;

        Team aFootballTeam = mockTeamObject(null, "http://crestUrl.com");

        mockResponseEntityBody(aFootballTeam);
        mockRestTemplateReturn(RESTFUL_URL, teamId);

        Team result = clubService.retrieveFootballClubById(teamId);
        assertEquals(aFootballTeam.getCrestUrl(), result.getCrestUrl());

    }

    @Test
    public void retreiveFootballClub_restTemplateUrlIsValidForGivenTeamId_urlIValidForTeam() {

        teamId = 66;

        String teamCrestUrl = "http://www.football-data.org/teams/" + teamId;

        mockResponseEntityBody(null);
        mockRestTemplateReturn(teamCrestUrl, teamId);
        when(restTemplate.exchange(teamCrestUrl, HttpMethod.GET, mockRequestHeaders(), Team.class))
                .thenReturn(responseEntity);

        clubService.retrieveFootballClubById(teamId);

        verify(restTemplate, times(1)).exchange(teamCrestUrl, HttpMethod.GET, mockRequestHeaders(), Team.class);

    }

    private Team mockTeamObject(String name, String crestUrl) {
        Team mockTeam = new Team();
        mockTeam.setName(name);
        mockTeam.setCrestUrl(crestUrl);

        return mockTeam;
    }

    private void mockRestTemplateReturn(String url, int teamId) {
        when(restTemplate.exchange(format("%s%s", url, teamId), HttpMethod.GET, mockRequestHeaders(), Team.class)).thenReturn(responseEntity);
    }

    private void mockResponseEntityBody(Team aFootballTeam) {
        when(responseEntity.getBody()).thenReturn(aFootballTeam);
    }


}
