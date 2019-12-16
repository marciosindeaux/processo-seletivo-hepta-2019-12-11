package com.hepta.mercado.rest;

import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;

import javax.ws.rs.client.WebTarget;

public abstract class AbstractControllerTest {

    protected static WebTarget service;
    protected static final String URL_LOCAL = "http://localhost:8080/mercado/rs/";
}
