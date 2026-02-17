package com.rick1135.SpringFolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransacaoRequestDto (
    @NotBlank(message = "O ticker é obrigatório")
    @Size(min=3, max=10, message = "O ticker deve conter entre 3 e 10 caracteres")
    String ticker,

    @NotNull(message = "O tipo da transação é obrigatório")
    String tipo,

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser positiva")
    BigDecimal quantidade,

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    BigDecimal precoUnitario,

    @NotNull(message = "A data da transação é obrigatória")
    String dataTransacao
) {}
