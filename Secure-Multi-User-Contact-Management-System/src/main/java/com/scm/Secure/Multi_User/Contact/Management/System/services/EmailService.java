package com.scm.Secure.Multi_User.Contact.Management.System.services;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendEmailWithHtml();

    void sendEmailWithAttachment();

}
