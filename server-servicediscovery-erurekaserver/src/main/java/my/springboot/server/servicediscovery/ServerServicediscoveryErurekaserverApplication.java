package my.springboot.server.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerServicediscoveryErurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerServicediscoveryErurekaserverApplication.class, args);
	}

}
