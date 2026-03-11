package com.scm.Secure.Multi_User.Contact.Management.System.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.Contact;
import com.scm.Secure.Multi_User.Contact.Management.System.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    long countByUser(User user);

    long countByUserAndFavorite(User user, boolean favorite);

    // find th contacts by user

    // custom finder method
    Page<Contact> findByUser(User user, Pageable pageable);

    // custom query method
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

    Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);

    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonenumberKeyword, Pageable pageable);

}
