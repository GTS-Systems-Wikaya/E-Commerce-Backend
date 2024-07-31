package projet.gtssystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class GtsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtsSystemApplication.class, args);
	}

}
