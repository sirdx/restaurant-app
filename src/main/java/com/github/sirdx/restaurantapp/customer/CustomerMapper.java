package com.github.sirdx.restaurantapp.customer;

import com.github.sirdx.restaurantapp.customer.dto.CustomerRequestDto;
import com.github.sirdx.restaurantapp.customer.dto.CustomerResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        return Customer.builder()
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .email(requestDto.email())
                .build();
    }

    public CustomerResponseDto fromCustomer(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCreatedAt()
        );
    }
}
