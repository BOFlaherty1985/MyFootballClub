package uk.co.myfootballclub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.service.impl.FixturesByTeamService;

/**
 * Fixtures By Days Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/12/2014
 * @project MyFootballClub
 */
@RestController
public class FixturesByDaysController {

    @Autowired
    private FixturesByTeamService fixturesByTeamService;

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(value="/retrieveFixturesByDays", method = RequestMethod.POST)
    public String retrieveFixturesByDays(@RequestParam("teamId") int teamId,
                                       @RequestParam("numberOfDays") int numberOfDays,
                                       @RequestParam("typeOfFixture") String typeOfFixture) throws InvalidFixtureTypeException, JsonProcessingException {

        return mapper.writeValueAsString(
                fixturesByTeamService.getFixturesByDays(teamId, typeOfFixture, numberOfDays));
    }


}
