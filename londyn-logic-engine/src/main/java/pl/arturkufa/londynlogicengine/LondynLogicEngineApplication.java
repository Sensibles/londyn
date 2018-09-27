package pl.arturkufa.londynlogicengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.arturkufa")
public class LondynLogicEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(LondynLogicEngineApplication.class, args);
	}
}
