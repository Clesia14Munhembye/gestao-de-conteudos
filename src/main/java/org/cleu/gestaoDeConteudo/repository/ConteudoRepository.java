package org.cleu.gestaoDeConteudo.repository;

import java.util.List;

import org.cleu.gestaoDeConteudo.model.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Integer> {

    @Query("""
                SELECT DISTINCT c FROM Conteudo c
                JOIN c.tarefas t
                WHERE c.id=t.conteudo.id and t.usuario.id = :usuarioId
                group BY c.id
            """)
    List<Conteudo> findConteudosByUsuarioId(@Param("usuarioId") Integer usuarioId);

}
