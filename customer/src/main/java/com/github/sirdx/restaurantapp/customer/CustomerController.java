package com.github.sirdx.restaurantapp.customer;

import com.github.sirdx.restaurantapp.customer.dto.CustomerRequestDto;
import com.github.sirdx.restaurantapp.customer.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<UUID> createCustomer(
            @RequestBody @Valid CustomerRequestDto requestDto
    ) {
        return ResponseEntity.ok(customerService.createCustomer(requestDto));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getById(
            @PathVariable UUID customerId
    ) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
}
