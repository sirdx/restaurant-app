package com.github.sirdx.restaurantapp.table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableRepository extends JpaRepository<Table, UUID> {

}
