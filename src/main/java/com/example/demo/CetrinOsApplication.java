// CetrinOsApplication.java (Spring Boot 메인 애플리케이션 클래스)
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class CetrinOsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CetrinOsApplication.class, args);
    }

}
