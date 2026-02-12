package com.rick1135.SpringFolio.repository;

import com.rick1135.SpringFolio.entity.Ativo;
import com.rick1135.SpringFolio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    Optional<Ativo> findByUsuarioAndTicker(Usuario usuario, String ticker);
}
