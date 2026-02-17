package com.rick1135.SpringFolio.controller;

import com.rick1135.SpringFolio.dto.AtivoResponseDto;
import com.rick1135.SpringFolio.mapper.Mapper;
import com.rick1135.SpringFolio.repository.AtivoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ativos")
public class AtivoController {
    private final AtivoRepository ativoRepository;

    public AtivoController(AtivoRepository ativoRepository) {
        this.ativoRepository = ativoRepository;
    }

    @GetMapping
    public List<AtivoResponseDto> listar(){
        return ativoRepository.findAll().stream()
                .map(Mapper::toDto).toList();
    }
}
