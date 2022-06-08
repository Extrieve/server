package io.getarrays.server;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.model.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ServerRepository serverRepository) {
		return args ->
			serverRepository.save(
					Server
							.builder()
							.ipAddress("192.168.0.1")
							.hostname("server1")
							.memory("8GB")
							.type("Arch Linux")
							.status(Status.SERVER_UP)
							.imageUrl("http://localhost:8080/server/images/server1.png")
							.build()
			);

	}
}