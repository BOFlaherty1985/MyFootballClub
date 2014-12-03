package uk.co.myfootballclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.league.League;
import uk.co.myfootballclub.model.Team;
import uk.co.myfootballclub.service.FixturesByDayService;
import uk.co.myfootballclub.service.LeagueByMatchDayService;
import uk.co.myfootballclub.service.WeatherForecastForFixtureService;

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
    @Autowired
    private WeatherForecastForFixtureService weatherForecastForFixtureService;

    private static final String DISPLAY_FOOTBALL_TEAM_VIEW = "displayFootballTeam";
    private static final int THIRTY_DAYS = 30;
    private static final int PREMIER_LEAGUE_ID = 354;

    @RequestMapping(value="/myFootballTeam")
    public ModelAndView myFootballTeamDisplay(ModelAndView mav) throws Exception {

        // TODO - remove hardcoded team id
        int teamId = 563;

        // TODO - Retrieve Logged In User
        // TODO - Logged in User has a team preference (teamId)

        // TODO - retrieve id from team object (team.getId()) using teamId property from the User
        mav.addObject(new Team());

        // Upcoming Fixtures
        mav.addObject("upcomingFixtures", retrieveFixturesByDays(teamId));

        // Past Results
        mav.addObject("recentResults", retrieveFixturesByDay(teamId));

        // League standings
        mav.addObject("leagueStandings", retrieveLeagueStandingsByLeagueId());

        // Retrieve weather forecast for next fixture - write service method to retrieve next fixture
        mav.addObject("weatherForFixture", weatherForecastForFixtureService.retrieveWeatherForecastForFixture(retrieveFixturesByDays(teamId)));

        mav.setViewName(DISPLAY_FOOTBALL_TEAM_VIEW);

        return mav;
    }

    private League retrieveLeagueStandingsByLeagueId() {
        return leagueByMatchDayService.retrieveLeagueStandings(PREMIER_LEAGUE_ID);
    }

    private List<Fixture> retrieveFixturesByDay(int teamId) throws InvalidFixtureTypeException {
        return fixturesByDayService.getFixturesByDays(teamId, "p", THIRTY_DAYS);
    }

    private List<Fixture> retrieveFixturesByDays(int teamId) throws InvalidFixtureTypeException {
        return fixturesByDayService.getFixturesByDays(teamId, "n", THIRTY_DAYS);
    }

}
