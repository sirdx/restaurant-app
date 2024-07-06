package com.github.sirdx.restaurantapp.table;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "restaurant_table")
@EntityListeners(AuditingEntityListener.class)
public class Table {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 8)
    private String location;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private Integer seats;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
