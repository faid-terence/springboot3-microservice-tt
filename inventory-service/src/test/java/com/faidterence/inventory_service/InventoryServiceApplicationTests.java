package com.faidterence.inventory_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {
	static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0"));

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldCheckInStock() {
		var response = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=sku-1&quantity=100")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);
		assertTrue(response);
	}
}