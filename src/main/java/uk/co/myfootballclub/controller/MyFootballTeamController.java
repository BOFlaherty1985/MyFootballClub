package uk.co.myfootballclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.co.myfootballclub.exception.ClubNameIsNotValidException;
import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.Team;
import uk.co.myfootballclub.model.league.League;
import uk.co.myfootballclub.persistence.domain.User;
import uk.co.myfootballclub.service.ClubDetailsService;
import uk.co.myfootballclub.service.FixturesByTeamService;
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
    private FixturesByTeamService fixturesService;
    @Autowired
    private LeagueByMatchDayService leagueService;
    @Autowired
    private WeatherForecastForFixtureService weatherService;
    @Autowired
    private ClubDetailsService clubDetailsService;

    private static final String DISPLAY_FOOTBALL_TEAM_VIEW = "displayFootballTeam";
    private static final int THIRTY_DAYS = 30;
    private static final int PREMIER_LEAGUE_ID = 354;

    @RequestMapping(value="/myFootballTeam")
    public ModelAndView myFootballTeamDisplay(ModelAndView mav) throws Exception {

        // add a new user object for user registration form
        mav.addObject(new User());

        // TODO - remove hardcoded team id
        int teamId = 563;

        // TODO - Retrieve Logged In User, Logged in User has a team preference (teamId)
        // TODO - retrieve id from team object (team.getId()) using teamId property from the User

        mav.addObject(new Team());

        displayClubDetails(mav, "westhamunitedfc"); // TODO - remove hardcoded clubName parameter

        displayNextFixtureAndCorrespondingWeather(mav, teamId);

        displayFixturesAndResultsFromLast30days(mav, teamId);

        displayLeagueTable(mav);

        mav.setViewName(DISPLAY_FOOTBALL_TEAM_VIEW);

        return mav;
    }

    private void displayClubDetails(ModelAndView mav, String clubName) throws Exception {
        // Display additional club information from json file

        try {
            mav.addObject("clubDetails", clubDetailsService.retrieveClubDetails("westhamunitedfc"));
        } catch (ClubNameIsNotValidException e) {
            // TODO - investigate why this error is being thrown? can't find json file.
            System.out.println("ClubNameIsNotValid");
        }

    }

    private void displayNextFixtureAndCorrespondingWeather(ModelAndView mav, int teamId) throws Exception {

        // Retrieve next fixture for given team
        Fixture nextFixture = fixturesService.getTeamsNextFixture(teamId);
        mav.addObject("teamsNextFixture", nextFixture);

        // Retrieve weather forecast for next fixture - write service method to retrieve next fixture
        mav.addObject("weatherForFixture", weatherService.retrieveWeatherForecastForFixture(nextFixture));

    }

    private void displayFixturesAndResultsFromLast30days(ModelAndView mav, int teamId) throws InvalidFixtureTypeException {

        // Upcoming Fixtures (next 30 days)
        mav.addObject("upcomingFixtures", retrieveFixturesByDays(teamId));

        // Past Results (last 30 days)
        mav.addObject("recentResults", retrieveFixturesByDay(teamId));

    }

    private void displayLeagueTable(ModelAndView mav) {

        // League standings
        mav.addObject("leagueStandings", retrieveLeagueStandingsByLeagueId());

    }

    private League retrieveLeagueStandingsByLeagueId() {
        return leagueService.retrieveLeagueStandings(PREMIER_LEAGUE_ID);
    }

    private List<Fixture> retrieveFixturesByDay(int teamId) throws InvalidFixtureTypeException {
        return fixturesService.getFixturesByDays(teamId, "p", THIRTY_DAYS);
    }

    private List<Fixture> retrieveFixturesByDays(int teamId) throws InvalidFixtureTypeException {
        return fixturesService.getFixturesByDays(teamId, "n", THIRTY_DAYS);
    }

}
