package org.cleu.gestaoDeConteudo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.cleu.gestaoDeConteudo.model.Plano;
import org.cleu.gestaoDeConteudo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {

    Optional<Plano> findByUsuario(Usuario usuario);

    @Query("SELECT a.validade FROM Plano a WHERE a.usuario.id = :usuarioId")
    LocalDate findValidadeByUsuarioId(@Param("usuarioId") Integer usuarioId);

}
