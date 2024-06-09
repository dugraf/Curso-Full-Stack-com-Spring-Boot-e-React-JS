package com.financas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.financas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    boolean existsByEmail(String email);
}
