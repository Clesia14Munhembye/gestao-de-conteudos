package org.cleu.gestaoDeConteudo.repository;

import org.cleu.gestaoDeConteudo.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface PlataformaRepository extends JpaRepository<Plataforma, Integer>{

}