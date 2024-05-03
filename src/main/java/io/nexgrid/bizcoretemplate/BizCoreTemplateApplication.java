package io.nexgrid.bizcoretemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
//@EnableMongoRepositories
public class BizCoreTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizCoreTemplateApplication.class, args);
	}

}
