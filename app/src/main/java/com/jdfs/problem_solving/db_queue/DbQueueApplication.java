package com.jdfs.problem_solving.db_queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DbQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbQueueApplication.class, args);
	}

}
