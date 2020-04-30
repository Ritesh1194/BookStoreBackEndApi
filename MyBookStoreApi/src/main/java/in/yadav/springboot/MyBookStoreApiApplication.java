package in.yadav.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
//(scanBasePackages = { "in.yadav.bookstore.SpringBookStoreApi.controller",
//		"in.yadav.bookstore.SpringBookStoreApi.repository", "in.yadav.bookstore.SpringBookStoreApi.service",
//		"in.yadav.bookstore.SpringBookStoreApi.service.impl" })
//@ComponentScan({ "in.yadav.bookstore.SpringBookStoreApi.controller", "in.yadav.bookstore.SpringBookStoreApi.repository",
//		"in.yadav.bookstore.SpringBookStoreApi.service", "in.yadav.bookstore.SpringBookStoreApi.service.impl" })
@EntityScan("in.yadav.springboot.model")
@EnableJpaRepositories("in.yadav.springboot.repository")
public class MyBookStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookStoreApiApplication.class, args);
	}
}