package uk.co.myfootballclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.co.myfootballclub.persistence.dao.UserRepository;
import uk.co.myfootballclub.persistence.domain.User;

/**
 * Proof Of Concept - Save User
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/12/2014
 * @project MyFootballClub
 */
@Controller
public class UserPOCController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/saveUser")
    public void saveUser() {

        User pocUser = new User();
        pocUser.setUsername("BOFlaherty");
        pocUser.setFirstName("Benjamin");
        pocUser.setLastName("OFlaherty");
        pocUser.setEmail("b_oflaherty@hotmail.com");
        pocUser.setMyFootballClub(10);

        userRepository.save(pocUser);
    }

    @RequestMapping(value="/findUser")
    public ModelAndView findUser(ModelAndView mav) {

        mav.setViewName("findUser");
        mav.addObject(userRepository.findOne(1L));

        return mav;
    }

}
