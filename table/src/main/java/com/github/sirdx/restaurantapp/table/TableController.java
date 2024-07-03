package com.github.sirdx.restaurantapp.table;

import com.github.sirdx.restaurantapp.table.dto.TableRequestDto;
import com.github.sirdx.restaurantapp.table.dto.TableResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @PostMapping
    public ResponseEntity<UUID> createTable(
            @RequestBody @Valid TableRequestDto requestDto
    ) {
        return ResponseEntity.ok(tableService.createTable(requestDto));
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<TableResponseDto> getTable(
            @PathVariable UUID tableId
    ) {
        return ResponseEntity.ok(tableService.findById(tableId));
    }

    @GetMapping
    public ResponseEntity<List<TableResponseDto>> getTables() {
        return ResponseEntity.ok(tableService.findAll());
    }
}
