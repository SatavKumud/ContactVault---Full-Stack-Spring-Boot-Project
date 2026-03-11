package com.scm.Secure.Multi_User.Contact.Management.System.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.User;
import com.scm.Secure.Multi_User.Contact.Management.System.repositories.ContactRepo;
import com.scm.Secure.Multi_User.Contact.Management.System.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private UserService userService;

    // dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard(Model model, Authentication authentication) {

        // Get logged-in user
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        // Get counts
        long contactCount = contactRepo.countByUser(user);
        long favouriteCount = contactRepo.countByUserAndFavorite(user, true);

        // Pass to template
        model.addAttribute("contactCount", contactCount);
        model.addAttribute("favouriteCount", favouriteCount);

        return "user/dashboard";
    }

    // user profile page

    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }

    // add contact page

    // view contacts

    // edit contact

    // delete contact

    // search contact

}
