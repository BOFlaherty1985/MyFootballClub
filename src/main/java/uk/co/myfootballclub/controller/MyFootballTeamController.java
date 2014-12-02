package uk.co.myfootballclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.Team;
import uk.co.myfootballclub.service.FixturesByDayService;
import uk.co.myfootballclub.service.LeagueByMatchDayService;

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
    @Autowired
    private LeagueByMatchDayService leagueByMatchDayService;

    private static final String DISPLAY_FOOTBALL_TEAM_VIEW = "displayFootballTeam";
    private static final int THIRTY_DAYS = 30;
    private static final int PREMIER_LEAGUE_ID = 354;

    @RequestMapping(value="/myFootballTeam")
    public ModelAndView myFootballTeamDisplay(ModelAndView mav) throws InvalidFixtureTypeException {

        // TODO - retrieve id from team object (team.getId())
        mav.addObject(new Team());

        // Upcoming Fixtures TODO - remove hardcoded team id
        List<Fixture> upcomingFixtures = fixturesByDayService.getFixturesByDays(563, "n", THIRTY_DAYS);
        mav.addObject("upcomingFixtures", upcomingFixtures);

        // Recent results TODO - remove hardcoded team id
        List<Fixture> recentResults = fixturesByDayService.getFixturesByDays(563, "p", THIRTY_DAYS);
        mav.addObject("recentResults", recentResults);

        // League standings
        mav.addObject("leagueStandings", leagueByMatchDayService.retrieveLeagueStandings(PREMIER_LEAGUE_ID));

        mav.setViewName(DISPLAY_FOOTBALL_TEAM_VIEW);

        return mav;
    }

}
