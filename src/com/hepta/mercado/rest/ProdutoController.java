package com.hepta.mercado.rest;

import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.service.ProdutoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;

@Path("/produtos")
public class ProdutoController {
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	ProdutoService produtoService;
	
	public ProdutoController() {
		produtoService = new ProdutoService();
	}
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * Adiciona novo produto no mercado
	 * 
	 * @param produto: Novo produto
	 * @return response 200 (OK) - Conseguiu adicionar
	 */
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response produtoCreate(Produto produto) {
		try{
			produtoService.salvarProduto(produto);
		}catch (Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).build();
	}
	
	/**
	 * Lista todos os produtos do mercado
	 * 
	 * @return response 200 (OK) - Conseguiu listar
	 */
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response produtoRead() {
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos = produtoService.listarProdutos();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		GenericEntity<List<Produto>> entity = new GenericEntity<List<Produto>>(produtos) {};
		return Response.status(Status.OK).entity(entity).build();
	}

	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response buscarPorId(@PathParam("id") Integer id){
		Produto produto = null;
		try{
			produto = produtoService.buscarPorId(id);
		}catch (Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity(new GenericEntity<Produto>(produto){}).build();
	}
	
	/**
	 * Atualiza um produto no mercado
	 * 
	 * @param id: id do produto
	 * @param produto: Produto atualizado
	 * @return response 200 (OK) - Conseguiu atualiza
	 */
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response produtoUpdate(@PathParam("id") Integer id, Produto produto) {

		try{
			produtoService.atualizarProduto(id, produto);
		}catch (Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).build();
	}
	
	/**
	 * Remove um produto do mercado
	 * 
	 * @param id: id do produto
	 * @return response 200 (OK) - Conseguiu remover
	 */
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response produtoDelete(@PathParam("id") Integer id) {
		try{
			produtoService.excluirProduto(id);
		}catch (Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).build();
	}

}
