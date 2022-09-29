package com.IcpcInformationSystemBackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.IcpcInformationSystemBackend.dao")
@EnableScheduling
public class IcpcInformationSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcpcInformationSystemBackendApplication.class, args);
    }

}


