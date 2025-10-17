package com.generation.gamoragames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.gamoragames.model.Produto;
import com.generation.gamoragames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	// Método para listar todos os produtos
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	// Método para listar produtos por ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
	    return produtoRepository.findById(id)
	        .map(resp -> ResponseEntity.ok(resp))
	        .orElse(ResponseEntity.notFound().build());
	}
	
	// Método para listar produtos por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	// Método para listar produtos por plataforma
	@GetMapping("/plataforma/{plataforma}")
	public ResponseEntity<List<Produto>> getByPlataforma(@PathVariable String plataforma) {
		return ResponseEntity.ok(produtoRepository.findAllByPlataformaContainingIgnoreCase(plataforma));
	}
	
	// Método para listar produtos com preço maior que o valor informado
	@GetMapping("/preco/maior/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMaior(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPrecoAsc(preco));
	}
	
	// Método para listar produtos com preço menor que o valor informado
	@GetMapping("/preco/menor/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMenor(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
	}
	
	// Método para criar um novo produto
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	// Método para atualizar um produto existente
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// Método para deletar um produto por ID
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
	}
	
}
