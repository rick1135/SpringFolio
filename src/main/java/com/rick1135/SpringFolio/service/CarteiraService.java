package com.rick1135.SpringFolio.service;

import com.rick1135.SpringFolio.entity.*;
import com.rick1135.SpringFolio.repository.AtivoRepository;
import com.rick1135.SpringFolio.repository.TransacaoRepository;
import com.rick1135.SpringFolio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CarteiraService {
    private final TransacaoRepository transacaoRepository;
    private final AtivoRepository ativoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarteiraService(TransacaoRepository transacaoRepository, AtivoRepository ativoRepository, UsuarioRepository usuarioRepository) {
        this.transacaoRepository = transacaoRepository;
        this.ativoRepository = ativoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Transacao registrarOperacao(Long UsuarioId, Transacao novaTransacao){
        Usuario usuario = usuarioRepository.findById(UsuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        novaTransacao.setUsuario(usuario);
        if(novaTransacao.getDataTransacao()==null)
            novaTransacao.setDataTransacao(LocalDate.now());
        transacaoRepository.save(novaTransacao);

        if(novaTransacao.getTipo()==TipoTransacao.COMPRA){
            processarCompra(usuario, novaTransacao);
        } else if (novaTransacao.getTipo()==TipoTransacao.VENDA){
            processarVenda(usuario, novaTransacao);
        }

        return novaTransacao;
    }

    private void processarCompra(Usuario usuario, Transacao novaTransacao){
        Ativo ativo = ativoRepository.findByUsuarioAndTicker(usuario, novaTransacao.getTicker())
                .orElse(new Ativo());

        if(ativo.getTicker()==null){
            ativo.setUsuario(usuario);
            ativo.setTicker(novaTransacao.getTicker());
            if(novaTransacao.getTipoAtivo()==null){
                throw new RuntimeException("O tipo de ativo é obrigatório!");
            }
            ativo.setTipo(novaTransacao.getTipoAtivo());
            ativo.setQuantidade(novaTransacao.getQuantidade());
            ativo.setPrecoMedioPago(novaTransacao.getPrecoUnitario());
        }else{
            BigDecimal totalAtual=ativo.getQuantidade().multiply(ativo.getPrecoMedioPago());
            BigDecimal totalCompra=novaTransacao.getQuantidade().multiply(novaTransacao.getPrecoUnitario());
            BigDecimal novaQuantidade=ativo.getQuantidade().add(novaTransacao.getQuantidade());
            if(novaQuantidade.compareTo(BigDecimal.ZERO)==0){
                ativo.setPrecoMedioPago(BigDecimal.ZERO);
            } else {
                BigDecimal novoPrecoMedio=totalAtual.add(totalCompra)
                        .divide(novaQuantidade, 2, RoundingMode.HALF_UP);
                ativo.setPrecoMedioPago(novoPrecoMedio);
            }

            ativo.setQuantidade(novaQuantidade);
        }
        ativoRepository.save(ativo);
    }

    private void processarVenda(Usuario usuario, Transacao novaTransacao){
        Ativo ativo = ativoRepository.findByUsuarioAndTicker(usuario, novaTransacao.getTicker())
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado na carteira"));

        if(ativo.getQuantidade().compareTo(novaTransacao.getQuantidade())<0){
            throw new RuntimeException("Quantidade insuficiente para venda");
        }

        BigDecimal novaQuantidade=ativo.getQuantidade().subtract(novaTransacao.getQuantidade());
        ativo.setQuantidade(novaQuantidade);

        if(novaQuantidade.compareTo(BigDecimal.ZERO)==0){
            ativoRepository.delete(ativo);
        }else{
            ativoRepository.save(ativo);
        }
    }
}
