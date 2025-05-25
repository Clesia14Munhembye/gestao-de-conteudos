package org.cleu.gestaoDeConteudo.repository;

import java.util.List;

import org.cleu.gestaoDeConteudo.model.PlataformaTarefa;
import org.cleu.gestaoDeConteudo.model.PlataformaTarefaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaTarefaRepository extends JpaRepository<PlataformaTarefa, PlataformaTarefaId>{

    @Query("SELECT p FROM PlataformaTarefa p join Tarefa t on p.id.tarefaId = t.id where t.usuario.id= :usuarioId")
    List<PlataformaTarefa> findByIdUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT count(p) FROM PlataformaTarefa p join Tarefa t on p.id.tarefaId = t.id where t.usuario.id= :usuarioId")
    int countByIdUsuarioId(@Param("usuarioId") Integer usuarioId);
}
