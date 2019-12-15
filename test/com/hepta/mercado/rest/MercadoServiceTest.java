package com.hepta.mercado.rest;

import com.hepta.mercado.builder.FabricanteBuilder;
import com.hepta.mercado.builder.ProdutoBuilder;
import com.hepta.mercado.entity.Produto;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MercadoServiceTest {

	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/mercado/rs/produtos";

	private static Produto produto;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		service = client.target( UriBuilder.fromUri(URL_LOCAL).build() );
	}

	@BeforeEach
	void setUpProduto(){
		produto = ProdutoBuilder.umProduto()
				.comVolume(Double.valueOf(2))
				.comUnidade("Unidades")
				.comNome("Camisa")
				.comEstoque(20)
				.comFabricante(
						FabricanteBuilder
								.umFabricante()
								.comNome("Vira Casaca")
								.agora()
				)
				.agora();
	}

	@Test
	void testListaTodosProdutos() {
		Response response = service.request().get();
		assertEquals(response.getStatusInfo().getStatusCode() , Response.Status.OK.getStatusCode());
	}

	@Test
	void testCadastrarNovoProduto(){
		Response response = service.request().post(Entity.entity(produto, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(response.getStatusInfo().getStatusCode() ,Response.Status.OK.getStatusCode());
	}

	@Test
	void naoDeveCadastrarNovoProdutoSemFabricante(){
		produto.setFabricante(null);
		Response response = service.request().post(Entity.entity(produto, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(response.getStatusInfo().getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}

}
