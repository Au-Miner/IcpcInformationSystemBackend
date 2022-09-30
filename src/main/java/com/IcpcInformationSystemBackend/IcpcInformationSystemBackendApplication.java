package com.IcpcInformationSystemBackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.IcpcInformationSystemBackend.dao")
public class IcpcInformationSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcpcInformationSystemBackendApplication.class, args);
    }

}


