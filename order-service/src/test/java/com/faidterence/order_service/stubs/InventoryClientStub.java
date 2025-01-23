package com.faidterence.order_service.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {


    public static void stubInventoryClient(String skuCode, Integer quantity) {
       stubFor(get(urlEqualTo("/api/inventory?" + "skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("true")));

    }
}
