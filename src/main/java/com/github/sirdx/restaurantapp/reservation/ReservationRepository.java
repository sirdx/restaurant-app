package com.github.sirdx.restaurantapp.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Set<Reservation> findAllByTableId(UUID tableId);
}
