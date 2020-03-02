package com.example.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.entity.Lancamento;
import com.example.demo.model.enums.StatusLancamento;
import com.example.demo.model.repository.LancamentoRepository;
import com.example.demo.model.repository.LancamentoRepositoryTest;
import com.example.demo.service.impl.LancamentoServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LancamentoServiceTest {

  @SpyBean
  LancamentoServiceImpl service;
  
  @MockBean
  LancamentoRepository repository;
  
  @Test
  public void deveSalvarUmLancamento() {
    //cenario
    Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
    Mockito.doNothing().when(service).validar(lancamentoASalvar);
    
    Lancamento lancamentoSalvo = LancamentoRepositoryTest.criarLancamento();
    lancamentoSalvo.setId(1l);
    Mockito.when(repository.save(lancamentoASalvar)).thenReturn(lancamentoSalvo);
    
    //
    Lancamento lancamento = service.salvar(lancamentoASalvar);
    
    //verificacao
    Assertions.assertThat(lancamento.getId()).isEqualTo(lancamento.getId());
    Assertions.assertThat(lancamento.getStatus()).isEqualTo(StatusLancamento.PENDENTE);
    
  }
  
  @Test
  public void naoDeveSavarUmLancamentoQuandoHouverErroDeValidacao() {
    
  }
  
}
