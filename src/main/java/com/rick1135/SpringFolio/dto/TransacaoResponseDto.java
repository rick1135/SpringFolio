package com.rick1135.SpringFolio.dto;

import com.rick1135.SpringFolio.entity.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoResponseDto (
        Long id,
        String ticker,
        TipoTransacao tipoTransacao,
        BigDecimal quantidade,
        BigDecimal precoUnitario,
        BigDecimal valorTotal,
        LocalDate dataTransacao
) {}
