package com.sqc.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        //scanBasePackages = "com.sqc.academy.baikiemtra"
        scanBasePackages = "com.sqc.academy.bai4"

)
public class AcademyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcademyApplication.class, args);
    }
}
