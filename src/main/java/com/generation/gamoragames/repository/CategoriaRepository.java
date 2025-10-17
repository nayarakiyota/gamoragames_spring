package com.generation.gamoragames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.gamoragames.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	// Buscar categorias pelo tipo
	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
	
	// Buscar categorias pelo id
	public List<Categoria> findAllById(Long id);

}
