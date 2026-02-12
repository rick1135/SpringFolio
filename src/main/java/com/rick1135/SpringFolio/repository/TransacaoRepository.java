package com.rick1135.SpringFolio.repository;

import com.rick1135.SpringFolio.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
