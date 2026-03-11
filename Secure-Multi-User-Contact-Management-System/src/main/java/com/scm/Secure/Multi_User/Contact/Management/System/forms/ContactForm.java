package com.scm.Secure.Multi_User.Contact.Management.System.forms;

import org.springframework.web.multipart.MultipartFile;

import com.scm.Secure.Multi_User.Contact.Management.System.Validators.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ContactForm {

    @NotBlank(message = "Contact name is required")
    @Size(min = 3)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;

    @Size(min = 8, max = 12, message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Address of contact is required")
    private String address;

    private String description;

    private boolean favorite;

    private String websiteLink;

    private String linkedInLink;

    @ValidFile(message = "Invalid file size")
    // annotation creation to valiate file
    private MultipartFile contactImage;

    private String picture;

}
