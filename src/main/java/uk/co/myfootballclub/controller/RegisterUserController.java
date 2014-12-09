package uk.co.myfootballclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.co.myfootballclub.persistence.dao.UserRepository;
import uk.co.myfootballclub.persistence.domain.User;

import javax.validation.Valid;

/**
 * Register A New User Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 09/12/2014
 * @project MyFootballClub
 */
@Controller
public class RegisterUserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value="/registerUser", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

        if(!result.hasErrors()) {
            repository.save(user);
        }

        return "redirect:/myFootballTeam";
    }

}
