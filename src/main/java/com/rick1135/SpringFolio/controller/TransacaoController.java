package com.rick1135.SpringFolio.controller;

import com.rick1135.SpringFolio.entity.Transacao;
import com.rick1135.SpringFolio.service.CarteiraService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final CarteiraService carteiraService;

    public TransacaoController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/{usuarioId}")
    public Transacao registrar(@PathVariable Long usuarioId, @RequestBody Transacao transacao){
        return carteiraService.registrarOperacao(usuarioId, transacao);
    }
}
