package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.Lancamento;
import com.example.demo.model.enums.StatusLancamento;

public interface LancamentoService {
  Lancamento salvar(Lancamento lancamento);
  Lancamento atualizar(Lancamento lancamento);
  void deletar(Lancamento lancamento);
  List<Lancamento> buscar(Lancamento lancamentoFiltro);
  void atualizarStatus(Lancamento lancamento, StatusLancamento status);
  void validar(Lancamento lancamento);
}
