package com.hepta.mercado.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.hepta.mercado.builder.FabricanteBuilder;
import com.hepta.mercado.builder.ProdutoBuilder;
import com.hepta.mercado.entity.Produto;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MercadoServiceTest {

	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/mercado/rs/produtos";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		service = client.target( UriBuilder.fromUri(URL_LOCAL).build() );
	}

	@Test
	void testListaTodosProdutos() {
		Response response = service.request().get();
		assertTrue(response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

	@Test
	public void testCadastrarNovoProduto(){
		Produto produto = ProdutoBuilder.umProduto()
				.comId(1)
				.comVolume(Double.valueOf(2))
				.comUnidade("Unidades")
				.comNome("Camisa")
				.comEstoque(20)
				.comFabricante(
						FabricanteBuilder
								.umFabricante()
								.comId(1)
								.agora()
				)
				.agora();
		Response response = service.request().post(Entity.entity(produto, MediaType.APPLICATION_JSON_TYPE));
	}

}
