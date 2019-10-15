package com.example.demospring4;

import com.example.demospring4.formatter.ProvinceFormatter;
import com.example.demospring4.service.CustomerService;
import com.example.demospring4.service.ProvinceService;
import com.example.demospring4.service.impl.CustomerServiceImplWithSpringData;
import com.example.demospring4.service.impl.ProvinceServiceImplWithSpringData;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class Demospring4Application {

    public static void main(String[] args) {
        SpringApplication.run(Demospring4Application.class, args); }
    @Bean
    public CustomerService customerService(){
        return new CustomerServiceImplWithSpringData();
    }
    @Bean
    public ProvinceService provinceService() {return  new ProvinceServiceImplWithSpringData();}

    @Configuration
    class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

        private ApplicationContext appContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            appContext = applicationContext;
        }

        @Override
        public void addFormatters(FormatterRegistry registry) {
            ProvinceService provinceService = appContext.getBean(ProvinceService.class);
            Formatter provinceFormatter = new ProvinceFormatter(provinceService);
            registry.addFormatter(provinceFormatter);
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
            interceptor.setParamName("lang");
            registry.addInterceptor(interceptor);
        }
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("message");
            return messageSource;
        }
        @Bean
        public LocaleResolver localeResolver() {
            SessionLocaleResolver localeResolver = new SessionLocaleResolver();
            localeResolver.setDefaultLocale(new Locale("en"));
            return localeResolver;
        }

    }

}

