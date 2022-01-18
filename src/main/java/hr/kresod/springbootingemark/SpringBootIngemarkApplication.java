package hr.kresod.springbootingemark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SpringBootIngemarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIngemarkApplication.class, args);
	}

}
