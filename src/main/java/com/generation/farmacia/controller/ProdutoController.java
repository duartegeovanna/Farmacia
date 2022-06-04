package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.ProdutoModel;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll() {
	    return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoModel>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<ProdutoModel>> getByPrecoInicio(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(inicio, fim));
	}
	
	@GetMapping("/nome/{nome}/oureceitaMedica/{receitaMedica}")
	public ResponseEntity<List<ProdutoModel>> getByNomeOuReceitaMedica(@PathVariable String nome, @PathVariable String receitaMedica){
		return ResponseEntity.ok(produtoRepository.findByNomeOrReceitaMedica(nome, receitaMedica));
	}
	
	@GetMapping("/nome/{nome}/ereceitaMedica/{receitaMedica}")
	public ResponseEntity<List<ProdutoModel>> getByNomeEReceitaMedica(@PathVariable String nome, @PathVariable String receitaMedica){
		return ResponseEntity.ok(produtoRepository.findByNomeAndReceitaMedica(nome, receitaMedica));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> postProdutoModel(@Valid @RequestBody ProdutoModel produtoModel){
		if (categoriaRepository.existsById(produtoModel.getCategoriaModel().getId())) 
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 	
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> putProdutoModel(@Valid @RequestBody ProdutoModel produtoModel){
		if (produtoRepository.existsById(produtoModel.getId())){
			if(categoriaRepository.existsById(produtoModel.getCategoriaModel().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutoModel(@PathVariable Long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
