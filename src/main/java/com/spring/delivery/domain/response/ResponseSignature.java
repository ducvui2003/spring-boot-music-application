package com.spring.delivery.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSignature {
    private String signature;
    @JsonProperty("api_key")
    private String apiKey;
    private long timestamp;
    private String folder;
    private String resourceType;
    private String publicId;
    private String type;
}
