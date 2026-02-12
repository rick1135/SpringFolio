package com.rick1135.SpringFolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "ativos")
@Data
public class Ativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 10)
    private String ticker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAtivo tipo;

    @Column(nullable = false, precision = 19, scale = 8)
    private BigDecimal quantidade;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal precoMedioPago;
}
