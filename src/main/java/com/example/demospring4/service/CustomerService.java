package com.example.demospring4.service;


import com.example.demospring4.model.Customer;
import com.example.demospring4.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class CustomerService extends AbstractEntityServiceImplWithSpringData<Customer, Long> {

    public abstract Page<Customer> findAll(Pageable pageInfo);

    public abstract List<Customer> search(String keyword);

    public abstract Page<Customer> search(String keyword, Pageable pageInfo);
    public abstract Iterable<Customer> findAllByProvince(Province province);
}
