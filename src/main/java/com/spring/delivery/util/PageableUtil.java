package com.spring.delivery.util;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.mapper.EntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageableUtil {
    public void checkNoEmpty(Page<?> pageable) {
        // Check if the pageable object is empty
        if (pageable.isEmpty()) {
            throw new RuntimeException("Pageable object is empty");
        }
    }

    public <T, R> ApiPaging<R> handlePaging(Page<T> page, EntityMapper<T, R> mapper) {
        ApiPaging<R> apiPaging = new ApiPaging<>();
        apiPaging.setTotalPages(page.getTotalPages());
        apiPaging.setLast(page.isLast());
        apiPaging.setFirst(page.isFirst());
        apiPaging.setCurrentPage(page.getNumber());
        apiPaging.setSize(page.getSize());
        apiPaging.setTotalItems(page.getTotalElements());
        // Apply the mapper to each content item
        apiPaging.setContent(page.getContent()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList()));

        return apiPaging;
    }
}
