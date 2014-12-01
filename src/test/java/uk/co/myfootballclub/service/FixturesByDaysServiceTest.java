package uk.co.myfootballclub.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;

import java.util.List;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Retrieves fixtures an integer number of days.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
public class FixturesByDaysServiceTest {

    @Autowired
    private RestTemplate integrationTemplate;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FixturesByDayService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // assert that the method getFixturesByDays(int x) return is not null
    @Test
    public void assertThatGetFixturesByDaysMethodDoesNotReturnNull() throws InvalidFixtureTypeException {
        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(new Fixture[1]);

        assertNotNull("getFixturesByDays() is not equal to null.", service.getFixturesByDays(563, "n", 1));
    }

    // verify that RestTemplate getObjectBy has been called the correct number of times.
    @Test
    public void verifyThatRestTemplateGetForObjectMethodHasBeenCalledOnceForObjectTypeString() throws InvalidFixtureTypeException {

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(new Fixture[1]);

        service.getFixturesByDays(563, "n", 1);
        verify(restTemplate, times(1)).getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any());

    }

    // assert that given a returned Json return from getFixturesByDays(int x) that the List<Fixture> is of the correct size
    @Test
    public void assertFixturesListIsEqualToOneBasedOnGivenJsonResponseFromRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[1];

        Fixture fixture = new Fixture();
        fixture.setHomeTeam("HomeTeam");

        json_array[0] = fixture;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "n", 1);
        assertEquals("FixtureList size equals 1.", 1, fixtureList.size());

    }

    @Test
    public void assertFixtureListISEqualToTwoBasedOnGivenJsonResponseFroMRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[2];

        Fixture fixture_one = new Fixture();
        fixture_one.setHomeTeam("HomeTeam1");

        json_array[0] = fixture_one;

        Fixture fixture_two = new Fixture();
        fixture_two.setHomeTeam("HomeTeam2");

        json_array[1] = fixture_two;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "n", 1);
        assertEquals("FixtureList size equals 2.", 2, fixtureList.size());

    }

    @Test
    public void assertFixtureListObjectHomeTeamHasTheCorrectValueBasedOnJsonReturnFromRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[1];

        Fixture fixture_one = new Fixture();
        fixture_one.setHomeTeam("HomeTeam1");

        json_array[0] = fixture_one;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "n", 1);
        Fixture aFixture = fixtureList.get(0);

        assertEquals("Fixture homeTeam value is correct.", "HomeTeam1", aFixture.getHomeTeam());

    }

    @Test
    public void assertFixtureListObjectAwayTeamHasTheCorrectValueBasedOnJsonReturnFromRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[1];

        Fixture fixture_one = new Fixture();
        fixture_one.setAwayTeam("AwayTeam1");

        json_array[0] = fixture_one;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "n", 1);
        Fixture aFixture = fixtureList.get(0);

        assertEquals("Fixture awayTeam value is correct.", "AwayTeam1", aFixture.getAwayTeam());

    }

    @Test
    public void assertFixtureListObjectGoalsHomeTeamHasTheCorrectValueBasedOnJsonReturnFromRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[1];

        Fixture fixture_one = new Fixture();
        fixture_one.setGoalsHomeTeam(1);

        json_array[0] = fixture_one;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "n", 1);
        Fixture aFixture = fixtureList.get(0);

        assertEquals("Fixture goalsHomeTeam value is correct.", 1, aFixture.getGoalsHomeTeam());

    }

    @Test
    public void assertFixtureListObjectGoalsAwayTeamHasTheCorrectValueBasedOnJsonReturnFromRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[1];

        Fixture fixture_one = new Fixture();
        fixture_one.setGoalsAwayTeam(2);

        json_array[0] = fixture_one;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "n", 1);
        Fixture aFixture = fixtureList.get(0);

        assertEquals("Fixture goalsAwayTeam value is correct.", 2, aFixture.getGoalsAwayTeam());

    }

    @Test
    public void assertFixtureListObjectDateHasTheCorrectValueBasedOnJsonReturnFromRESTService() throws InvalidFixtureTypeException {

        Fixture[] json_array = new Fixture[1];

        Fixture fixture_one = new Fixture();
        fixture_one.setDate(new Date());

        json_array[0] = fixture_one;

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(json_array);

        List<Fixture> fixtureList = service.getFixturesByDays(563, "p", 1);
        Fixture aFixture = fixtureList.get(0);

        assertNotNull("Fixture Date value is correct.", aFixture.getDate());

    }

    @Test
    public void throwExceptionWhenTypeOfFixtureIsNotEqualToNorP() throws InvalidFixtureTypeException {

        when(restTemplate.getForObject(Mockito.<String> any(), Mockito.<Class<Fixture[]>>any())).thenReturn(null);

        try {
            service.getFixturesByDays(563, "x", 5);
            fail("TypeOfFixture is not equal to 'n' or 'p'");
        } catch (InvalidFixtureTypeException e) {
            assertEquals("assert InvalidFixtureTypeException message is valid.",
                    "Invalid FixtureType has been entered.", e.getMessage());
        }

    }

    @Test
    public void restTemplateIntegrationTestOne() {

        Fixture[] fixtureList = integrationTemplate.getForObject("http://www.football-data.org/teams/563/fixtures?timeFrame=p5", Fixture[].class);
        assertEquals("FixtureList size is equal to 1.", 1, fixtureList.length);

    }

    @Test
    public void restTemplateIntegrationTestTwo() {

        Fixture[] fixtureList = integrationTemplate.getForObject("http://www.football-data.org/teams/563/fixtures?timeFrame=p1", Fixture[].class);
        assertEquals("FixtureList size is equal to 0.", 0, fixtureList.length);

    }

    @Test
    public void restTemplateIntegrationTestThree() {

        Fixture[] fixtureList = integrationTemplate.getForObject("http://www.football-data.org/teams/563/fixtures?timeFrame=p14", Fixture[].class);
        assertEquals("FixtureList size is equal to 2.", 2, fixtureList.length);

    }

}