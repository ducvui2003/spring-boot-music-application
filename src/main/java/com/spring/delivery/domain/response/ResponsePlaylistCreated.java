package com.spring.delivery.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePlaylistCreated {
    Integer id;
    String name;
    String description;
    String cover;
    Boolean isPublic;
}
