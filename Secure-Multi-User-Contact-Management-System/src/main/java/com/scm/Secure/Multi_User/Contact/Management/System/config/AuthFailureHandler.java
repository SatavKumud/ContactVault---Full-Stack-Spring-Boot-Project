package com.scm.Secure.Multi_User.Contact.Management.System.config;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.scm.Secure.Multi_User.Contact.Management.System.helpers.Message;
import com.scm.Secure.Multi_User.Contact.Management.System.helpers.MessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        // ADD THIS - check your console
        System.out.println("Exception type: " + exception.getClass().getName());
        System.out.println("Exception message: " + exception.getMessage());

        HttpSession session = request.getSession();

        if (exception instanceof DisabledException) {
            session.setAttribute("message",
                    Message.builder()
                            .content("User is disabled, Email with verification link is sent on your email id.")
                            .type(MessageType.red)
                            .build());

        } else {
            session.setAttribute("message",
                    Message.builder()
                            .content("Invalid email or password.")
                            .type(MessageType.red)
                            .build());
        }

        response.sendRedirect("/login");
    }
}