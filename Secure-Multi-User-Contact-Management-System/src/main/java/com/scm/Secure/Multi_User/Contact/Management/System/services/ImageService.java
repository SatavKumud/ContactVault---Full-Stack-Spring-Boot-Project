package com.scm.Secure.Multi_User.Contact.Management.System.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile contactImage, String fileName);

    String getUrlFromPublicId(String publicId);

}
