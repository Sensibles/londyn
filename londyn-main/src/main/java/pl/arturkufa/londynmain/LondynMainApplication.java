package pl.arturkufa.londynmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.arturkufa")
public class LondynMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(LondynMainApplication.class, args);
	}
}
