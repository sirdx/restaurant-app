package com.github.sirdx.restaurantapp.table;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "table-service",
        url = "${application.config.table-url}"
)
public interface TableClient {
    @GetMapping("/{tableId}")
    Optional<TableResponseDto> getTable(@PathVariable UUID tableId);

    @GetMapping
    List<TableResponseDto> getTables();
}
