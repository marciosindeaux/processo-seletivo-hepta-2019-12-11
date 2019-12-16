package com.hepta.mercado.rest.produto;

import com.hepta.mercado.builder.FabricanteBuilder;
import com.hepta.mercado.builder.ProdutoBuilder;
import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.rest.AbstractControllerTest;
import com.hepta.mercado.utils.ConverterUtils;
import com.hepta.mercado.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoControllerTest extends AbstractControllerTest {

	private static Produto produto;

	@BeforeEach
	void setUpProduto(){
		service = TestUtils.generateWebTarget(URL_LOCAL,"produtos");

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
		assertEquals(Response.Status.OK.getStatusCode(),response.getStatusInfo().getStatusCode());
	}

	@Test
	void testCadastrarNovoProduto(){
		Response response = service.request().post(Entity.entity(produto, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.OK.getStatusCode(),response.getStatusInfo().getStatusCode() );
	}

	@Test
	void naoDeveCadastrarNovoProdutoSemFabricante() throws InterruptedException {
		produto.setFabricante(null);
		Response response = service.request().post(Entity.entity(produto, MediaType.APPLICATION_JSON_TYPE));
		assertEquals( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),response.getStatusInfo().getStatusCode());
	}

	@Test
	void deveExcluirItem() throws InterruptedException {
		try{

			//Cadastrar um produt antes de Exclui-lo

			Response response = service.request().post(Entity.entity(produto, MediaType.APPLICATION_JSON_TYPE));
			assertEquals(Response.Status.OK.getStatusCode(),response.getStatusInfo().getStatusCode() );

			//Requisitar todos os produtos para pegar o ultimo (cadastrado)

			response = service.request().get();
			response.bufferEntity();
			List<Produto> produtos = (List<Produto>) response.readEntity(List.class)
					.stream()
					.map(item -> ConverterUtils.convertTo(item, Produto.class))
					.collect(Collectors.toList());

			Produto produtoInserido = produtos.stream().reduce((a,b) -> b).orElseThrow(NoSuchElementException::new);

			service = TestUtils.generateWebTarget(URL_LOCAL, "produtos/"+produtoInserido.getId());
			response = service.request().delete();
			assertEquals(Response.Status.OK.getStatusCode(),response.getStatusInfo().getStatusCode());

		}catch(Exception e){
			throw e;
		}
	}

}
