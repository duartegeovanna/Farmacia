package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categorias")
public class CategoriaModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Descrição é obrigatório e não pode possuir espaços em branco")
	@Size(min = 4, max = 100, message = "O atributo Descrição precisa ter no mínimo 4 e máximo 100 caracteres.")
	private String descricao;
	
	@NotBlank(message = "O atributo Estoque é obrigatório e não pode possuir espaços em branco")
	@Size(min = 4, max = 100, message = "O atributo Estoque precisa ter no mínimo 4 e máximo 100 caracteres.")
	private String estoque;
	
	@OneToMany(mappedBy = "categoriaModel", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoriaModel")
	private List<ProdutoModel> produtoModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public List<ProdutoModel> getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(List<ProdutoModel> produtoModel) {
		this.produtoModel = produtoModel;
	}
	
	

}
