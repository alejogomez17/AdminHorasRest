package com.desarrollo.adminhoras.main;

import com.desarrollo.adminhoras.api.AplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PersistenceJPAConfig.class,AplicationController.class})
public class AdminhorasApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(AdminhorasApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(AdminhorasApplication.class);
    }
}
