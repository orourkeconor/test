package co.copper.test;

import co.copper.test.datamodel.Test;
import co.copper.test.storage.TestJavaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

	private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertTestData(TestJavaRepository repository) {
		return (args) -> {
			repository.save(Test.builder().val("ValueA").build());
			for (Test t: repository.findAll()) {
				log.info(t.toString());
			}
		};
	}
}
