package com.laksdev.training.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student laks= new Student(
                    "Tangguh",
                    "tangguhlaksana0@gmail.com",
                    LocalDate.of(2003, Month.JUNE,2)
            );
            Student albar= new Student(
                    "Albar",
                    "albar@gmail.com",
                    LocalDate.of(2011, Month.OCTOBER,18)
            );
            //repository.saveAll(List.of(albar,laks));
        };
    }
}
