package br.com.alurafood.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
/*
@EnableEurekaServer - Com isso, ele já consegue entender que é uma aplicação do Eureka,
que é um servidor e vamos conseguir ver no navegador para podermos
aguardar os registros dos microsserviços.
 */
@EnableEurekaServer
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
