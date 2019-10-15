package com.example.demospring4.repository;

import com.example.demospring4.model.Customer;
import com.example.demospring4.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);
    Iterable<Customer> findAllByFirstNameOrLastName(String firstName, String lastName);

    Page<Customer> findAllByFirstNameContainsOrLastNameContains(String firstName, String lastName, Pageable pageable);
}

