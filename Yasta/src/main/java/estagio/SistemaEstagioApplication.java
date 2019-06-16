package estagio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
@EntityScan(basePackages="estagio.model")
@ComponentScan(basePackages= {"estagio.*"})
@EnableJpaRepositories(basePackages= {"estagio.repository"})

public class SistemaEstagioApplication extends SpringBootServletInitializer implements WebMvcConfigurer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SistemaEstagioApplication.class);
	}
	
	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaEstagioApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
	}
}

//RF Prontos 1, 2, 3, 4, 5, 6, 7, 8, 12, 13;
//RF Por fazer 9, 13, 16
//RF Não feitos 10, 11, 14, 15, 17, 18
