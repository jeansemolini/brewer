package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.helper.estilo.EstilosQueries;

@Repository						  //para qual classe   tipo chave primaria
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{
	
	//optional pq pode ou nao existir
	public Optional<Estilo> findByNomeIgnoreCase(String nome);	

}
