package org.cleu.gestaoDeConteudo.repository;

import org.cleu.gestaoDeConteudo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
