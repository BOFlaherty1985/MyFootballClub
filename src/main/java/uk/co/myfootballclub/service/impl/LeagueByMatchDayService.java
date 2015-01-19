package uk.co.myfootballclub.service.impl;

/**
 * Retrieve a League Object for a given Match Day.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/12/2014
 * @project MyFootballClub
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.model.league.League;
import uk.co.myfootballclub.service.interfaces.ILeagueService;

import static java.lang.String.format;

@Service
public class LeagueByMatchDayService extends AbstractService implements ILeagueService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String LEAGUE_STANDING_DATA_URL = "http://api.football-data.org/alpha/soccerseasons";

    @Override
    public League retrieveDataByInt(int input) {

        ResponseEntity<League> leagueRequest = restTemplate.exchange(format("%s/%s/leagueTable", LEAGUE_STANDING_DATA_URL, input),
                HttpMethod.GET, generateRequestHeaders(), League.class);

        return leagueRequest.getBody();
    }

    public League retrieveLeagueStandingsByMatchDay(int leagueId, int matchDay) {

        ResponseEntity<League> leagueRequest = restTemplate.exchange(format("%s/%s/leagueTable?matchday=%s",
                        LEAGUE_STANDING_DATA_URL, leagueId, matchDay), HttpMethod.GET, generateRequestHeaders(),
                        League.class);

        return leagueRequest.getBody();
    }

}
