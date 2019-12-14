package com.hepta.mercado.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUTO")
	private Integer id;

	@NotNull(message = "É obrigatório o nome do produto")
	@Column(name = "NOME")
	private String nome;

	@NotNull(message = "É Necessário a identificação do Fabricante")
	@ManyToOne
	@JoinColumn(name = "ID_FABRICANTE")
	private Fabricante fabricante;

	@NotNull(message = "É obrigatória a identificação do Volume")
	@Column(name = "VOLUME")
	private Double volume;

	@NotNull(message = "É obrigatória a identificação da Unidade")
	@Column(name = "UNIDADE")
	private String unidade;

	@NotNull(message = "É obrigatória a informação em estoque")
	@Column(name = "ESTOQUE")
	private Integer estoque;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
}
