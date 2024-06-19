package com.eoft.blog2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.eoft.blog2")

public class Blog2Application {

    public static void main(String[] args) {

        SpringApplication.run(Blog2Application.class, args);

    }

}
//#47.97.153.243 服务器ip1
// http://122.51.5.227:8081/
// 122.51.5.227:8081
