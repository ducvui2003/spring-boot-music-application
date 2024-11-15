package com.spring.delivery.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePlaylistCreated {
    String name;
    String description;
    String coverUrl;
    boolean isPublic;
    List<Long> songIds;
}
