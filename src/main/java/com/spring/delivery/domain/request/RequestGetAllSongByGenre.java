package com.spring.delivery.domain.request;
import com.spring.delivery.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestGetAllSongByGenre {
    String name;
    String description;
}
