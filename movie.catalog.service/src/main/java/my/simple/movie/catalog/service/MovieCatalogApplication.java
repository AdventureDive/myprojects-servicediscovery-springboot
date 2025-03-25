package my.simple.movie.catalog.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableHystrix
public class MovieCatalogApplication {
	
	@Bean	
	@LoadBalanced
	RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectionRequestTimeout(3000);
		
		return new RestTemplate(clientHttpRequestFactory);

	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogApplication.class, args);
	}

}
