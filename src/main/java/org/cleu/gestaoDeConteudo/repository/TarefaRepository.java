package org.cleu.gestaoDeConteudo.repository;

import org.cleu.gestaoDeConteudo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa , Integer>{

}
