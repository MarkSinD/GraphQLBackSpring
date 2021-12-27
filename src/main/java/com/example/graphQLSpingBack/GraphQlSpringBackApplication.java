package com.example.graphQLSpingBack;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;


@SpringBootApplication
public class GraphQlSpringBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(GraphQlSpringBackApplication.class, args);
	}

	@Bean
	GraphQLScalarType uploadScalarType() {
		return ApolloScalars.Upload;
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

	@Bean
	public MessageDigest messageDigest() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA1");
	}

}
