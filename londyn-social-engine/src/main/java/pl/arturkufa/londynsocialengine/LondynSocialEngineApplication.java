package pl.arturkufa.londynsocialengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.arturkufa")
public class LondynSocialEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(LondynSocialEngineApplication.class, args);
	}
}
