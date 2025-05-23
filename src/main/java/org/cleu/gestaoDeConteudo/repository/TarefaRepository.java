package org.cleu.gestaoDeConteudo.repository;

import java.util.List;

import org.cleu.gestaoDeConteudo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query("SELECT a FROM Tarefa a WHERE a.usuario.id = :usuarioId")
    List<Tarefa> findByUsuarioId(@Param("usuarioId") Integer usuarioId) ;

    @Query("SELECT count(a.id) FROM Tarefa a WHERE a.usuario.id = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT count(distinct a.conteudo) FROM Tarefa a WHERE a.usuario.id = :usuarioId")
    long countByContedoAndUsuarioId(@Param("usuarioId") Integer usuarioId);
}
