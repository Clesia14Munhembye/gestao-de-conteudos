package org.cleu.gestaoDeConteudo.repository;

import org.cleu.gestaoDeConteudo.model.PlataformaTarefa;
import org.cleu.gestaoDeConteudo.model.PlataformaTarefaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaTarefaRepository extends JpaRepository<PlataformaTarefa, PlataformaTarefaId>{

}
