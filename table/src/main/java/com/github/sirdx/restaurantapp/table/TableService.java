package com.github.sirdx.restaurantapp.table;

import com.github.sirdx.restaurantapp.table.exception.TableNotFoundException;
import com.github.sirdx.restaurantapp.table.dto.TableRequestDto;
import com.github.sirdx.restaurantapp.table.dto.TableResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    public UUID createTable(TableRequestDto requestDto) {
        var table = tableRepository.save(tableMapper.toTable(requestDto));
        return table.getId();
    }

    public TableResponseDto findById(UUID tableId) {
        return tableRepository.findById(tableId)
                .map(tableMapper::toTableResponse)
                .orElseThrow(() -> new TableNotFoundException(
                        String.format("Table with ID %s not found", tableId)
                ));
    }

    public List<TableResponseDto> findAll() {
        return tableRepository.findAll()
                .stream()
                .map(tableMapper::toTableResponse)
                .toList();
    }
}
