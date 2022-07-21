package br.com.alura.comex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class ComexApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ComexApplication.class, args);
  }

  @Override
  public void run(String... args) {
    //System.out.println(new BCryptPasswordEncoder().encode("123456"));
  }


}
