/**
 * Class: ValidationImage
 *
 * @author ducvui2003
 * @created 13/10/24
 */
package com.spring.delivery.util.validation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("validationImage")
public class ValidationImage implements ValidationStrategy {
    @Override
    public boolean isValid(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        return contentType != null && contentType.startsWith("image");
    }
}
