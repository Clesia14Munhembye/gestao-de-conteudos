package org.cleu.gestaoDeConteudo.repository;

import org.cleu.gestaoDeConteudo.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {

}
