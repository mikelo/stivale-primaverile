package ibm.com.stivaleprimaverile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StivaleprimaverileApplication {

	public static void main(String[] args) {
		SpringApplication.run(StivaleprimaverileApplication.class, args);
	}

}
