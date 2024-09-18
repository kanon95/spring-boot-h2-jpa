package com.practice.springh2jpa.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.practice.*")
@EntityScan(basePackages = {"com.practice.*"})
public class Springh2jpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(Springh2jpaApplication.class, args);
  }

}
