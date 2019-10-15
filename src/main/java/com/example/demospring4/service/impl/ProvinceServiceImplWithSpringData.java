package com.example.demospring4.service.impl;

import com.example.demospring4.model.Province;
import com.example.demospring4.repository.ProvinceRepository;
import com.example.demospring4.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class ProvinceServiceImplWithSpringData extends ProvinceService {
    @Autowired
    private ProvinceRepository repository;

    @Override
    protected CrudRepository<Province, Long> repository() {
        return repository;
    }
}