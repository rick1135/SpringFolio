package com.rick1135.SpringFolio.controller;

import com.rick1135.SpringFolio.dto.TransacaoRequestDto;
import com.rick1135.SpringFolio.dto.TransacaoResponseDto;
import com.rick1135.SpringFolio.entity.Transacao;
import com.rick1135.SpringFolio.mapper.Mapper;
import com.rick1135.SpringFolio.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final CarteiraService carteiraService;

    public TransacaoController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDto registrar(@PathVariable Long usuarioId, @RequestBody @Valid TransacaoRequestDto dto){
        Transacao novaTransacao= Mapper.toEntity(dto);
        Transacao transacaoSalva = carteiraService.registrarOperacao(usuarioId, novaTransacao);
        return Mapper.toDto(transacaoSalva);
    }
}
