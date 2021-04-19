package ua.serfer.ws.albums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlbumServiceApplicationDemo {

	public static void main(String[] args) {
		SpringApplication.run(AlbumServiceApplicationDemo.class, args);
	}

}

