package com.generation.lojadegames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Categoria é obrigatório!")
	@Size(max = 100, message = "O atributo Categoria deve conter no máximo 100 caracteres")
	private String tipo;
	
	@NotBlank(message = "O atributo Descrição é obrigatório!")
	@Size(min = 10, max = 500, message = "O atributo Descrição deve conter no mínimo 10 e no máximo 500 caracteres")
	private String descricao;
	
	@OneToMany(mappedBy = "categoria")
	@JsonIgnoreProperties("categoria")
	private List<Produto> produtos;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return tipo;
	}

	public void setCategoria(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
