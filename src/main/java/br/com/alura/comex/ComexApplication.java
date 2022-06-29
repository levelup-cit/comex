package br.com.alura.comex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@ComponentScan("br.com.alura.comex.*")
public class ComexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);
	}
}