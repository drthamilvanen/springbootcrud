package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner (StudentRepo studentRepo){
        return args -> {
            Student thamil= new Student(
                    "Thamilvanan",
                    "drthamilvanen@gmail.com",
                    LocalDate.of(1973, Month.APRIL, 24)
            );
            Student charan= new Student(
                    "Charan",
                    "Charanthamil@gmail.com",
                    LocalDate.of(2007, Month.NOVEMBER, 21)
            );
            studentRepo.saveAll(
                    Arrays.asList(thamil,charan)
            );
        };
    }
}
