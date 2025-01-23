package com.faidterence.product_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
// run on random test to avoid port conflicts with main application
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();

	}

	@Test
	void shouldCreateProduct() {

		String requestBody = """
				{
					"name": "Iphone 16 pro",
					"description": "This is an Iphone from Apple 2024",
					"price": 1000000
				}
		
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("name", org.hamcrest.Matchers.equalTo("Iphone 16 pro"), "description", org.hamcrest.Matchers.equalTo("This is an Iphone from Apple 2024"), "price", org.hamcrest.Matchers.equalTo(1000000));
	}

}
