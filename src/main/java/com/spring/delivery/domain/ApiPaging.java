package com.spring.delivery.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiPaging<T> {
    long totalItems;
    boolean isLast;
    boolean isFirst;
    long totalPages;
    int currentPage;
    int size;
    List<T> content;
}
