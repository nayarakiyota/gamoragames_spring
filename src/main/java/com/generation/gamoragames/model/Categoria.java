package com.generation.gamoragames.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id // PRIMARY KEY (id)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	
	@NotBlank(message = "O atributo tipo é obrigatório!")
	private String tipo;
	
	 @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	    private List<Produto> produtos;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
