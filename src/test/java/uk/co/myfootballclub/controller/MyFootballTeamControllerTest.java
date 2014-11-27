package uk.co.myfootballclub.controller;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.model.Team;

/**
 * Entry page that displays a users football team and displays the data.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/11/2014
 * @project MyFootballClub
 */
public class MyFootballTeamControllerTest {

    @Test
    public void test() {

        RestTemplate restTemplate = new RestTemplate();

        Team myTeam = restTemplate.getForObject("http://www.football-data.org/teams/563",
                Team.class);

        System.out.println(myTeam.toString());

    }


}
