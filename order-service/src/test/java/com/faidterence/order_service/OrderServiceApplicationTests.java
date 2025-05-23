package com.faidterence.order_service;

import com.faidterence.order_service.Client.InventoryClient;
import com.faidterence.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import java.util.regex.Matcher;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
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
	void shouldPlaceOrder() {

		String requestBody = """
				{
					"skuCode": "iphone_16_pro",
					"price": 10000,
					"quantity": 1
				}
		
				""";
		InventoryClientStub.stubInventoryClient("sku_1", 100);

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201)
				.body(org.hamcrest.Matchers.equalTo("Order placed successfully"));
//		assertThat(responseBodyString(), Matcher.is("Order placed successfully"));
	}

}
