package io.nexgrid.bizcoretemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BizCoreTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizCoreTemplateApplication.class, args);
	}

}
