package com.leonde.seconddawn.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


public class BaseTestSupport {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    protected String getBaseUriForOrders(){
        return String.format("http://localhost:%d/orders", serverPort);
    }

    protected String getBaseUriForJeeps(){
        return String.format("http://localhost:%d/jeeps", serverPort);
    }

}
