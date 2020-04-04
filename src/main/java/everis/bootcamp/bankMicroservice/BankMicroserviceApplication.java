package everis.bootcamp.bankMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@SpringBootApplication
public class BankMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankMicroserviceApplication.class, args);
	}

}
