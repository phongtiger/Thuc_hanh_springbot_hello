package com.example.demospring4.formatter;

import com.example.demospring4.model.Province;
import com.example.demospring4.service.ProvinceService;
import org.springframework.format.Formatter;


import java.text.ParseException;
import java.util.Locale;

public class ProvinceFormatter implements Formatter<Province> {
    private ProvinceService provinceService;

    public ProvinceFormatter(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        return provinceService.findOne(Long.valueOf(text));
    }

    @Override
    public String print(Province object, Locale locale) {
        return object.toString();
    }
}
