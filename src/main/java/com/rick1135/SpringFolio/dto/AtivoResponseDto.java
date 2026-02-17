package com.rick1135.SpringFolio.dto;

import com.rick1135.SpringFolio.entity.TipoAtivo;

import java.math.BigDecimal;

public record AtivoResponseDto (
      String ticker,
      TipoAtivo tipoAtivo,
      BigDecimal quantidade,
      BigDecimal precoMedio,
      BigDecimal valorTotalInvestido
) {}
