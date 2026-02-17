package com.rick1135.SpringFolio.repository;

import com.rick1135.SpringFolio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
