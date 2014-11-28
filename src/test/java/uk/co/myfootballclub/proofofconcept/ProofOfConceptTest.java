package uk.co.myfootballclub.proofofconcept;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.RestTestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.Team;

import java.util.Arrays;

/**
 * Entry page that displays a users football team and displays the data.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/11/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestTestConfig.class, WebInitializer.class})
public class ProofOfConceptTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void proofOfConcept() {

        Team myTeam = restTemplate.getForObject("http://www.football-data.org/teams/563",
                Team.class);

        System.out.println(myTeam.toString());

    }

    @Test
    public void proofOfConceptRetrieveListOfTeams() {

        Team[] teamList = restTemplate.getForObject("http://www.football-data.org/soccerseasons/354/teams",
                Team[].class);

        for(Team team : Arrays.asList(teamList)) {
            System.out.println(team.getName());
        }

    }

}
