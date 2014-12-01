package uk.co.myfootballclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.League;
import uk.co.myfootballclub.model.Team;
import uk.co.myfootballclub.service.FixturesByDayService;

import java.util.List;

/**
 * My football Team Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/11/2014
 * @project MyFootballClub
 */
@Controller
public class MyFootballTeamController {

    @Autowired
    private FixturesByDayService fixturesByDayService;

    @RequestMapping(value="/myFootballTeam")
    public ModelAndView myFootballTeamDisplay(ModelAndView mav) throws InvalidFixtureTypeException {

        mav.addObject(new Team());

        // Upcoming Fixtures TODO - remove hardcoded values
        List<Fixture> upcomingFixtures = fixturesByDayService.getFixturesByDays(563, "n", 30);
        mav.addObject("upcomingFixtures", upcomingFixtures);

        // Recent results TODO - remove hardcoded values
        List<Fixture> recentResults = fixturesByDayService.getFixturesByDays(563, "p", 30);
        mav.addObject("recentResults", recentResults);

        mav.addObject(new League());

        mav.setViewName("displayFootballTeam");

        return mav;
    }

}
