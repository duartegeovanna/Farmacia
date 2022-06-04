package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.CategoriaModel;


@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>{
	
	public List <CategoriaModel> findAllByDescricaoContainingIgnoreCase (@Param("descricao") String descricao); 

	public List <CategoriaModel> findAllByEstoqueContainingIgnoreCase (@Param("estoque") String estoque); 

}
