package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.delivery.util.enums.Tag;
import lombok.Builder;

@Builder
public record RequestCreateResource(String name,
                                    Tag tag,
                                    @JsonProperty("public_id")
                                    String publicId
) {
}
