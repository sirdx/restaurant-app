package com.github.sirdx.restaurantapp.table;

import com.github.sirdx.restaurantapp.table.dto.TableRequestDto;
import com.github.sirdx.restaurantapp.table.dto.TableResponseDto;
import org.springframework.stereotype.Service;

@Service
public class TableMapper {
    public Table toTable(TableRequestDto requestDto) {
        return Table.builder()
                .location(requestDto.location())
                .description(requestDto.description())
                .seats(requestDto.seats())
                .build();
    }

    public TableResponseDto toTableResponse(Table table) {
        return new TableResponseDto(
                table.getId(),
                table.getLocation(),
                table.getDescription(),
                table.getSeats(),
                table.getCreatedAt()
        );
    }
}
