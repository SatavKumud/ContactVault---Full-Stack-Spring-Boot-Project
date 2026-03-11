package com.scm.Secure.Multi_User.Contact.Management.System.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.User;
import com.scm.Secure.Multi_User.Contact.Management.System.helpers.Message;
import com.scm.Secure.Multi_User.Contact.Management.System.helpers.MessageType;
import com.scm.Secure.Multi_User.Contact.Management.System.repositories.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    // verifyemail
    @GetMapping("/verify-email")
    public String verifyemail(
            @RequestParam("token") String token,
            HttpSession session) {

        User user = userRepo.findByEmailToken(token).orElse(null);

        if (user != null) {

            System.out.println("=== VERIFY EMAIL CALLED ===");
            System.out.println("Token received: " + token);

            // User user = userRepo.findByEmailToken(token).orElse(null);

            System.out.println("User found: " + user);
            System.out.println("User email token: " + (user != null ? user.getEmailToken() : "USER IS NULL"));

            if (user.getEmailToken().equals(token)) {
                user.setEmailVerified(true);
                user.setEnabled(true);
                user.setEmailToken(null);

                userRepo.save(user);

                session.setAttribute("message",
                        Message
                                .builder()
                                .type(MessageType.green)
                                .content("Email verified! Now you can login ")
                                .build()

                );
                return "success_page";
            }

            session.setAttribute("message",
                    Message
                            .builder()
                            .type(MessageType.red)
                            .content("Email is not verified!! Something went wrong")
                            .build()

            );
            return "error_page";

        }

        session.setAttribute("message",
                Message
                        .builder()
                        .type(MessageType.red)
                        .content("Email is not verified!! Something went wrong")
                        .build()

        );

        return "error_page";
    }

}
