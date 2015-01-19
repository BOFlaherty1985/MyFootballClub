package uk.co.myfootballclub.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.service.interfaces.IFixtureService;

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
public class FixturesByTeamService extends AbstractService implements IFixtureService {

    private static final String FIXTURES_DATA_URL = "http://api.football-data.org/alpha/teams/";

    @Autowired
    private RestTemplate restTemplate;

    private static final String NEXT_FIXTURE = "n";
    private static final String PREVIOUS_FIXTURE = "p";

    /**
     * getFixturesByDays() returns next or past fixture (results) for a given team based on the number of days parameter.
     *
     * @param teamId id of required team (int).
     * @param fixtureType a fixtureType of either 'n' (next) or 'p' (past) is required.
     * @param numberOfDays number of days to return next/past fixtures for.
     * @return
     * @throws InvalidFixtureTypeException
     */
    public List<Fixture> getFixturesByDays(int teamId, String fixtureType, int numberOfDays) throws InvalidFixtureTypeException {

        ResponseEntity<Fixture[]> fixturesResp;

        if(fixtureType.equals(NEXT_FIXTURE) || fixtureType.equals(PREVIOUS_FIXTURE)) {

            fixturesResp = restTemplate.exchange(format("%s%s/fixtures?timeFrame=%s%s", FIXTURES_DATA_URL,
                    teamId, fixtureType, numberOfDays), HttpMethod.GET, generateRequestHeaders(), Fixture[].class);

        } else {
            throw new InvalidFixtureTypeException("Invalid FixtureType has been entered.");
        }

        Fixture[] fixturesList = fixturesResp.getBody();

        // reverse previous fixtures array to display latest fixture first
        if(fixtureType.equals(PREVIOUS_FIXTURE)) {
            ArrayUtils.reverse(fixturesList);
        }

        return Arrays.asList(fixturesList);
    }

    @Override
    public Fixture retrieveDataByInt(int input) {

        ResponseEntity<Fixture[]> fixtureRequest = restTemplate.exchange(format("%s%s/fixtures?timeFrame=n7",FIXTURES_DATA_URL, input),
                HttpMethod.GET, generateRequestHeaders(), Fixture[].class);

        Fixture[] fixture = fixtureRequest.getBody();

        return fixture[0];
    }

    public String getNextOpponentForTeam(Fixture nextFixture, String myFootballClub) {
        return (nextFixture.getHomeTeam().equals(myFootballClub) ? nextFixture.getAwayTeam() :
                nextFixture.getHomeTeam());
    }

}
