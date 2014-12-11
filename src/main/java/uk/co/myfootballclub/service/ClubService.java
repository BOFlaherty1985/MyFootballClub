package uk.co.myfootballclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.model.Team;

import static java.lang.String.format;

/**
 * Retrieves a football club from football-data.org
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/12/2014
 * @project MyFootballClub
 */
@Service
public class ClubService extends AbstractService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String TEAM_RESTFUL_URL = "http://www.football-data.org/teams/";

    public Team retrieveFootballClubById(int teamId) {
        return restTemplate.exchange(format("%s%s", TEAM_RESTFUL_URL, teamId)
                , HttpMethod.GET, generateRequestHeaders(), Team.class).getBody();
    }

}
