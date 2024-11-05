/**
 * Class: VaidationUtil
 *
 * @author ducvui2003
 * @created 13/10/24
 */
package com.spring.delivery.util.validation;

import org.springframework.web.multipart.MultipartFile;

public interface ValidationStrategy {
    boolean isValid(MultipartFile multipartFile);
}
