package com.generation.gamoragames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos") //CRIANDO A TABELA NO BANCO DE DADOS
public class Produto {
	
	@Id //INDICA QUE É A CHAVE PRIMÁRIA
	@GeneratedValue(strategy = GenerationType.IDENTITY) //INDICA QUE O VALOR SERÁ GERADO AUTOMATICAMENTE
	private Long id;
	
	// Nome do Jogo
	@Column(length = 100)
	@NotBlank(message = "O nome do produto é obrigatório.")
	private String nome;
	
	// Descrição do Jogo
	@Column(length = 500)
	@NotBlank(message = "A descrição do produto é obrigatória.")
	@Size(min = 5, max = 500, message = "A descrição deve ter no mínimo 5 e no máximo 500 caracteres.")
	private String descricao;
	
	// Preço do Jogo
	@Column(nullable = false, precision = 10, scale = 2)
	@DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
	@Digits(integer = 10, fraction = 2, message = "O preço deve ter até 10 dígitos inteiros e 2 decimais.")
	@NotNull(message = "O preço do produto é obrigatório.")
	private BigDecimal preco;
	
	// Plataforma do Jogo
	@Column(length = 50)
	@NotBlank(message = "A plataforma do produto é obrigatória.")
	private String plataforma;
	
	// Imagem do Jogo
	@Column(name = "imagem_url", nullable = false)
	@NotBlank(message = "A imagem do produto é obrigatória.")
	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	

}
