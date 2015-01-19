package uk.co.myfootballclub.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.model.Fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service to retrieve past form (results) vs. chosen teams next opponent.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/12/2014
 * @project MyFootballClub
 */
@Service
public class PastFormVsNextOpponentService extends AbstractService {

    @Autowired
    private RestTemplate restTemplate;

    private static final int NUMBER_OF_YEARS = 3;

    public List<Fixture> retrievePastFormAgainstNextOpponent(int teamId, String nextOpponent) {

        List<Fixture> responseResultList = new ArrayList<Fixture>();

        validateInputParameters(teamId, nextOpponent);

        retrieveResultsByYear(teamId, responseResultList, new DateTime().getYear());

        return filterResultsListByNextOpponent(responseResultList, nextOpponent);
    }

    private List<Fixture> filterResultsListByNextOpponent(List<Fixture> responseResultList, String nextOpponent) {

        List<Fixture> resultList = new ArrayList<Fixture>();

        for(Fixture previousResult : responseResultList) {

            if(previousResult.getHomeTeam().equals(nextOpponent) ||
                    previousResult.getAwayTeam().equals(nextOpponent)) {

                resultList.add(previousResult);

            }

        }

        return resultList;
    }

    private void validateInputParameters(int teamId, String nextOpponent) {

        if(teamId == 0) throw new IllegalArgumentException("TeamId must not be equal to 0.");

        if(nextOpponent == null) throw new NullPointerException("NextOpponent must not be null.");

    }

    private void retrieveResultsByYear(int teamId, List<Fixture> resultList, int currentYear) {

        // retrieve results for a maximum NUMBER_OF_YEARS
        for(int i = 0; i < NUMBER_OF_YEARS; i++) {

            ResponseEntity<Fixture[]> teamResultsByYear = restTemplate.exchange("http://api.football-data.org/alpha/teams/" +
                            teamId + "/fixtures?season=" + currentYear, HttpMethod.GET, generateRequestHeaders(),
                            Fixture[].class);

            // do not continue if object is null (empty)
            if(teamResultsByYear != null) {

                // add fixture objects to resultList
                buildResultsListFromResponse(resultList,
                        Arrays.asList(teamResultsByYear.getBody()));

            }

            currentYear--;
        }

    }

    private void buildResultsListFromResponse(List<Fixture> resultList, List<Fixture> teamResults) {

        for(Fixture result : teamResults) {
            resultList.add(result);
        }

    }
}
