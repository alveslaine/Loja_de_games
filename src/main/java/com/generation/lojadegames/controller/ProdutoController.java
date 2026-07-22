package com.generation.lojadegames.controller;

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

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.CategoriaRepository;
import com.generation.lojadegames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping // LISTAR TODOS
	public ResponseEntity <List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}") // PROCURAR PRODUTO POR ID
	public ResponseEntity <Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}") // PROCURAR PRODUTO POR NOME
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
	    return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/valor_menor/{valor}") // LISTAR PRODUTO POR VALOR MENOR
	public ResponseEntity<List<Produto>> getAllByValorMenor(@PathVariable BigDecimal valor){
	    return ResponseEntity.ok(produtoRepository.findByValorLessThan(valor));
	}

	@GetMapping("/valor_maior/{valor}") // LISTAR PRODUTO POR VALOR MAIOR
	public ResponseEntity<List<Produto>> getAllByValorMaior(@PathVariable BigDecimal valor){
	    return ResponseEntity.ok(produtoRepository.findByValorGreaterThan(valor));
	}
	
	@PostMapping // CADASTRAR NOVO PRODUTO
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		if (categoriaRepository.existsById(produto.getCategoria().getId())) {
			
		produto.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
	}
	
	@PutMapping // ATUALIZAR PRODUTO
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		if(produtoRepository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getCategoria().getId())) {
				return ResponseEntity.ok(produtoRepository.save(produto));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") // DELETAR PRODUTO
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);			
			produtoRepository.deleteById(id);
	}
}
