package com.scm.Secure.Multi_User.Contact.Management.System.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.User;
import com.scm.Secure.Multi_User.Contact.Management.System.forms.UserForm;
import com.scm.Secure.Multi_User.Contact.Management.System.helpers.Helper;
import com.scm.Secure.Multi_User.Contact.Management.System.helpers.Message;
import com.scm.Secure.Multi_User.Contact.Management.System.helpers.MessageType;
import com.scm.Secure.Multi_User.Contact.Management.System.services.EmailService;
import com.scm.Secure.Multi_User.Contact.Management.System.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Kumud Satav");
        model.addAttribute("kom", "Learing Springboot");
        model.addAttribute("link", "https://in.linkedin.com/in/kumud-satav-3bb281266");

        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("islogin", false);
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    // this is loging controller

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // do registration
    @RequestMapping("/signup")
    public String signupPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing register form... ");

        // fetch form data
        System.out.println(userForm);
        // validate that data
        if (rBindingResult.hasErrors()) {
            System.out.println("Errors found in form data");
            return "signup";
        }
        // save to databse
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .provider(com.scm.Secure.Multi_User.Contact.Management.System.entities.Providers.SELF)
        // .build();

        User user = new User();
        // user.setUserId(UUID.randomUUID().toString());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);

        // ADD THESE 2 LINES - generate and set token BEFORE saving
        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);
        System.out.println("user saved");

        // ADD THIS - send verification email AFTER saving
        String verificationLink = Helper.getLinkForEmailVerification(savedUser.getEmailToken());

        // In PageController, before emailService.sendEmail()
        System.out.println("=== SENDING EMAIL TO: " + savedUser.getEmail());
        emailService.sendEmail(
                savedUser.getEmail(),
                "Verify your ContactVault Account",
                "Hi " + savedUser.getName() + ",\n\n" +
                        "Please click below to verify:\n\n" +
                        verificationLink + "\n\nThanks,\nContactVault Team");

        Message message = Message.builder()
                .content("Registration successful!  Please check your email to verify your account.")
                .type(MessageType.green).build();
        // message - "Registration successful"
        session.setAttribute("message", message);
        // redirect
        return "redirect:/signup";
    }

}
