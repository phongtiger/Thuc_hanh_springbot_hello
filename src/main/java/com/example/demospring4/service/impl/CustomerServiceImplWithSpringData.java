package com.example.demospring4.service.impl;


import com.example.demospring4.model.Customer;
import com.example.demospring4.model.Province;
import com.example.demospring4.repository.CustomerRepository;
import com.example.demospring4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImplWithSpringData extends CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageInfo) {
        return customerRepository.findAll(pageInfo);
    }

    @Override
    public List<Customer> search(String keyword) {
        Iterable<Customer> searchResult = customerRepository
                .findAllByFirstNameOrLastName(keyword ,keyword);
        return streamAll(searchResult).collect(Collectors.toList());
    }

    @Override
    public Page<Customer> search(String keyword, Pageable pageInfo) {
        return customerRepository
                .findAllByFirstNameContainsOrLastNameContains(keyword, keyword, pageInfo);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    protected CrudRepository<Customer, Long> repository() {
        return customerRepository;
    }
}

