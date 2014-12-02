package uk.co.myfootballclub.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class FixturesByDayService {

    private static final String FIXTURES_DATA_URL = "http://www.football-data.org";

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

        Fixture[] fixtures;

        if(fixtureType.equals("n") || fixtureType.equals("p")) {
            fixtures = restTemplate.getForObject(format("%s/teams/%s/fixtures?timeFrame=%s%s", FIXTURES_DATA_URL,
                    teamId, fixtureType, numberOfDays), Fixture[].class);
        } else {
            throw new InvalidFixtureTypeException("Invalid FixtureType has been entered.");
        }

        return Arrays.asList(fixtures);
    }
}
