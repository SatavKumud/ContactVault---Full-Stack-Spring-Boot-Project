package com.scm.Secure.Multi_User.Contact.Management.System.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.Contact;
import com.scm.Secure.Multi_User.Contact.Management.System.services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private ContactService contactService;

    //get contact of user method
    @GetMapping("/contacts/{contactId}")
    public Contact geContact(@PathVariable String contactId){
        return contactService.getById(contactId);
    }

}
