/**
 * Class: ValidationAudio
 *
 * @author ducvui2003
 * @created 13/10/24
 */
package com.spring.delivery.util.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("validationAudio")
public class ValidationAudio implements ValidationStrategy {
    @Override
    public boolean isValid(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        return contentType != null && contentType.startsWith("audio");
    }
}
