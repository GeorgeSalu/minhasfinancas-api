package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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
  public Lancamento salvar(Lancamento lancamento) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Lancamento autualizar(Lancamento lancamento) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deletar(Lancamento lancamento) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
    // TODO Auto-generated method stub
    
  }

}
