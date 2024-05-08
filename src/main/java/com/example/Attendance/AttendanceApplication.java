package com.example.Attendance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
//@Configuration
@ComponentScan(basePackages = "com.example.Attendance")
public class AttendanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

}
