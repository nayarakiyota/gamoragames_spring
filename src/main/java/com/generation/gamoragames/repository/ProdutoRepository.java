package com.generation.gamoragames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.gamoragames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	// Buscar produtos por Id
    List<Produto> findAllById(Long id);
	
	// Buscar produtos pelo nome
	List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
	// Buscar produtos pela plataforma
    List<Produto> findAllByPlataformaContainingIgnoreCase(String plataforma);
    
    // Buscar produtos com preço maior que o valor informado, ordenados pelo preço em ordem crescente
    List<Produto> findByPrecoGreaterThanOrderByPrecoAsc(BigDecimal preco);
    
    // Buscar produtos com preço menor que o valor informado, ordenados pelo preço em ordem decrescente
    List<Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
}
