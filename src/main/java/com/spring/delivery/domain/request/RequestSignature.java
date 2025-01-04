package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.delivery.util.enums.Tag;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RequestSignature {
    @JsonProperty("name_file")
    String publicId;
    Tag tag;
}
