package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Lancamento;
import com.example.demo.model.enums.StatusLancamento;
import com.example.demo.model.repository.LancamentoRepository;
import com.example.demo.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

  private LancamentoRepository repository;
  
  public LancamentoServiceImpl(LancamentoRepository repository) {
    this.repository = repository;
  }
  
  @Override
  @Transactional
  public Lancamento salvar(Lancamento lancamento) {
    return repository.save(lancamento);
  }

  @Override
  @Transactional
  public Lancamento atualizar(Lancamento lancamento) {
    Objects.requireNonNull(lancamento.getId());
    return repository.save(lancamento);
  }

  @Override
  @Transactional
  public void deletar(Lancamento lancamento) {
    Objects.requireNonNull(lancamento.getId());
    repository.delete(lancamento);
  }

  @Override
  public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
    lancamento.setStatus(status);
    atualizar(lancamento);
  }

}
