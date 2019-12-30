package com.hepta.mercado.rest;

import javax.ws.rs.client.WebTarget;

public abstract class AbstractControllerTest {

    protected static WebTarget service;
    protected static final String URL_LOCAL = "http://localhost:8080/mercado/rs/";
}
