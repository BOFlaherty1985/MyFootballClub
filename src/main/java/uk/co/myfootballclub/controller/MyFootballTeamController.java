package uk.co.myfootballclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.League;
import uk.co.myfootballclub.model.Team;

/**
 * My football Team Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/11/2014
 * @project MyFootballClub
 */
@Controller
public class MyFootballTeamController {

    @RequestMapping(value="/myFootballTeam")
    public ModelAndView myFootballTeamDisplay(ModelAndView mav) {

        mav.addObject(new Team());
        mav.addObject(new Fixture());
        mav.addObject(new League());

        mav.setViewName("displayFootballTeam");

        return mav;
    }

}
