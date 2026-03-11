package com.scm.Secure.Multi_User.Contact.Management.System.Validators;

import org.springframework.web.multipart.MultipartFile;
import javax.imageio.*;
import java.awt.image.*;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Max;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB

    // type

    // height

    // width

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if (file == null || file.isEmpty()) {

            // context.disableDefaultConstraintViolation();
            // context.buildConstraintViolationWithTemplate("File cannot be
            // empty").addConstraintViolation();
            return true;
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 2MB").addConstraintViolation();
            return false;
        }

        // resolution
        // try {

        // BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        // if(bufferedImage.getWidth())
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        return true;

    }

}
