package uk.co.myfootballclub.service;

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

import static java.lang.String.format;

@Service
public class LeagueByMatchDayService extends AbstractService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String LEAGUE_STANDING_DATA_URL = "http://www.football-data.org/soccerseasons";

    public League retrieveLeagueStandings(int leagueId) {

        ResponseEntity<League> leagueRequest = restTemplate.exchange(format("%s/%s/ranking", LEAGUE_STANDING_DATA_URL, leagueId),
                HttpMethod.GET, generateRequestHeaders(), League.class);

        return leagueRequest.getBody();
    }

    public League retrieveLeagueStandingsByMatchDay(int leagueId, int matchDay) {

        ResponseEntity<League> leagueRequest = restTemplate.exchange(format("%s/%s/ranking?matchday=%s",
                        LEAGUE_STANDING_DATA_URL, leagueId, matchDay), HttpMethod.GET, generateRequestHeaders(),
                        League.class);

        return leagueRequest.getBody();
    }

}
