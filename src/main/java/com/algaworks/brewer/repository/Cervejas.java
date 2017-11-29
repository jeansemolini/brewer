package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.helper.cerveja.CervejasQueries;

@Repository                       //para qual classe     tipo chave primaria
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries {
	
}
