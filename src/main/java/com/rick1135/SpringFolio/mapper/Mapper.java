package com.rick1135.SpringFolio.mapper;

import com.rick1135.SpringFolio.dto.AtivoResponseDto;
import com.rick1135.SpringFolio.dto.TransacaoRequestDto;
import com.rick1135.SpringFolio.dto.TransacaoResponseDto;
import com.rick1135.SpringFolio.entity.Ativo;
import com.rick1135.SpringFolio.entity.Transacao;

public class Mapper {
    public static Transacao toEntity(TransacaoRequestDto dto){
        Transacao transacao = new Transacao();
        transacao.setTicker(dto.ticker().toUpperCase());
        transacao.setTipo(dto.tipo());
        transacao.setQuantidade(dto.quantidade());
        transacao.setPrecoUnitario(dto.precoUnitario());
        transacao.setDataTransacao(dto.dataTransacao());
        transacao.setTipoAtivo(dto.tipoAtivo());
        return transacao;
    }

    public static TransacaoResponseDto toDto(Transacao entity){
        return new TransacaoResponseDto(
                entity.getId(),
                entity.getTicker(),
                entity.getTipo(),
                entity.getQuantidade(),
                entity.getPrecoUnitario(),
                entity.getQuantidade().multiply(entity.getPrecoUnitario()),
                entity.getDataTransacao()
        );
    }

    public static AtivoResponseDto toDto(Ativo entity){
        return new AtivoResponseDto(
                entity.getTicker(),
                entity.getTipo(),
                entity.getQuantidade(),
                entity.getPrecoMedioPago(),
                entity.getQuantidade().multiply(entity.getPrecoMedioPago())
        );
    }
}
