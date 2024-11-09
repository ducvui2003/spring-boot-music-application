package com.spring.delivery.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseAuthentication {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonIgnore
    private String refreshToken;

    private UserDTO user;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserGetAccount {
        private UserDTO user;
    }

    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record UserDTO(long id, String email, String fullName) {
        public static UserDTO initFromMapInfoUserDTO(Map<String, Object> mapUser) {
            return UserDTO.builder()
                    .id((Long) mapUser.get("id"))
                    .email((String) mapUser.get("email"))
                    .fullName((String) mapUser.get("fullName"))
                    .build();
        }
    }
}
