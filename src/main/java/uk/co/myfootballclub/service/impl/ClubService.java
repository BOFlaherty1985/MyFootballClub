package uk.co.myfootballclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.model.Team;
import uk.co.myfootballclub.service.interfaces.ITeamService;

import static java.lang.String.format;

/**
 * Retrieves a football club from football-data.org
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/12/2014
 * @project MyFootballClub
 */
@Service
public class ClubService extends AbstractService implements ITeamService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String TEAM_RESTFUL_URL = "http://api.football-data.org/teams/";

    @Override
    public Team retrieveDataByInt(int input) {
        return restTemplate.exchange(format("%s%s", TEAM_RESTFUL_URL, input)
                , HttpMethod.GET, generateRequestHeaders(), Team.class).getBody();
    }

}
