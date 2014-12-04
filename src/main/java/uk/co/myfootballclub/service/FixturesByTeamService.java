package uk.co.myfootballclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

/**
 * Fixtures By Day Service
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 01/12/2014
 * @project MyFootballClub
 */
@Service
public class FixturesByTeamService extends AbstractService {

    private static final String FIXTURES_DATA_URL = "http://www.football-data.org/teams/";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * getFixturesByDays() returns next or past fixture (results) for a given team based on the number of days parameter.
     *
     * @param teamId id of required team (int).
     * @param fixtureType a fixtureType of either 'n' (next) or 'p' (past) is required.
     * @param numberOfDays number of days to return next/past fixtures for.
     * @return
     * @throws InvalidFixtureTypeException
     */
    public List<Fixture> getFixturesByDays(Object teamId, Object fixtureType, Object numberOfDays) throws InvalidFixtureTypeException {

        ResponseEntity<Fixture[]> fixturesResp;

        if(fixtureType.equals("n") || fixtureType.equals("p")) {

            fixturesResp = restTemplate.exchange(format("%s%s/fixtures?timeFrame=%s%s", FIXTURES_DATA_URL,
                    teamId, fixtureType, numberOfDays), HttpMethod.GET, generateRequestHeaders(), Fixture[].class);

        } else {
            throw new InvalidFixtureTypeException("Invalid FixtureType has been entered.");
        }

        Fixture[] fixturesList = fixturesResp.getBody();

        return Arrays.asList(fixturesList);
    }

    public Fixture getTeamsNextFixture(int teamId) {

        ResponseEntity<Fixture[]> fixtureRequest = restTemplate.exchange(format("%s%s/fixtures?timeFrame=n7",FIXTURES_DATA_URL, teamId),
                HttpMethod.GET, generateRequestHeaders(), Fixture[].class);

        Fixture[] fixture = fixtureRequest.getBody();

        return fixture[0];
    }

}
