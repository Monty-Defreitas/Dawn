package com.leonde.seconddawn.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


public class BaseTestSupport {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    protected String getBaseUriForOrdersEndPoint(String endPoint){
        return String.format("http://localhost:%d/orders" + endPoint, serverPort);
    }

    protected String getBaseUriForOrders(String urlPath) {
        return String.format("http://localhost:%d" + urlPath,serverPort);
    }

    protected String getBaseUriForJeeps(){
        return String.format("http://localhost:%d/jeeps", serverPort);
    }

    protected TestRestTemplate getRestTemplate() {
        return restTemplate;
    }

}
