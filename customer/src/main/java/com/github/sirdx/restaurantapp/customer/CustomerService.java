package com.github.sirdx.restaurantapp.customer;

import com.github.sirdx.restaurantapp.customer.dto.CustomerRequestDto;
import com.github.sirdx.restaurantapp.customer.dto.CustomerResponseDto;
import com.github.sirdx.restaurantapp.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public UUID createCustomer(CustomerRequestDto requestDto) {
        var customer = customerRepository.save(customerMapper.toCustomer(requestDto));
        return customer.getId();
    }

    public CustomerResponseDto findById(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID %s not found", customerId)
                ));
    }
}
