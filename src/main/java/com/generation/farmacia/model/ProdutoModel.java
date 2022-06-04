package com.generation.farmacia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class ProdutoModel {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Tipo é Nome e não pode possuir espaços em branco")
	@Size(min = 1, max = 100, message = "O Nome Tipo precisa ter no mínimo 1 e máximo 100 caracteres.")
	private String nome;
	
	@NotNull(message = "O atributo Quantidade é obrigatório")
	private Long quantidade;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message="Preço é obrigatório!")
	@Positive(message = "Digite um valor maior do que zero")
	private BigDecimal preco;
	
	@Column(name = "data_validade")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "O atributo Data do Lançamento é Obrigatório!")
	private LocalDate dataValidade;
	
	@NotBlank(message = "O atributo Tipo é Receita Médica e não pode possuir espaços em branco")
	@Size(min = 5, max = 100, message = "O Receita Médica Tipo precisa ter no mínimo 5 e máximo 100 caracteres.")
	private String receitaMedica;
	
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("produtoModel")
	private CategoriaModel categoriaModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getReceitaMedica() {
		return receitaMedica;
	}

	public void setReceitaMedica(String receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public CategoriaModel getCategoriaModel() {
		return categoriaModel;
	}

	public void setCategoriaModel(CategoriaModel categoriaModel) {
		this.categoriaModel = categoriaModel;
	}
	
	
}
