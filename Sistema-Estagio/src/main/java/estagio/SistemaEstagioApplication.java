package estagio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages="estagio.model")
@ComponentScan(basePackages= {"estagio.*"})
@EnableJpaRepositories(basePackages= {"estagio.repository"})

public class SistemaEstagioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEstagioApplication.class, args);
	}

}
