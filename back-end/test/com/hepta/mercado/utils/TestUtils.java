package com.hepta.mercado.utils;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

public class TestUtils {

    public static WebTarget generateWebTarget(String urlLocal, String set){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target( UriBuilder.fromUri(urlLocal + set).build() );
    }
}
