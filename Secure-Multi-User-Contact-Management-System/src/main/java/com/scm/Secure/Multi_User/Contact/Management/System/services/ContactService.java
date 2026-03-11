package com.scm.Secure.Multi_User.Contact.Management.System.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.Contact;
import com.scm.Secure.Multi_User.Contact.Management.System.entities.User;

public interface ContactService {

    // savecontacts
    Contact save(Contact contact);

    // update
    Contact update(Contact contact);

    // get Contact
    List<Contact> getAll();

    // get Contact by id
    Contact getById(String id);

    // delete
    void delete(String id);

    // search
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phonenumberKeyword, int size, int page, String sortBy, String order,
            User user);

    // get contact b userId
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);

}
