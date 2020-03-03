package com.example.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.exception.RegraNegocioException;
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
    Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
    Mockito.doThrow(RegraNegocioException.class).when(service).validar(lancamentoASalvar);
    
    Assertions.catchThrowableOfType(() -> service.salvar(lancamentoASalvar), RegraNegocioException.class);
    Mockito.verify(repository, Mockito.never()).save(lancamentoASalvar);
  }
  
  @Test
  public void deveAtualizarUmLancamento() {
    //cenario
    Lancamento lancamentoSalvo = LancamentoRepositoryTest.criarLancamento();
    lancamentoSalvo.setId(1l);
    lancamentoSalvo.setStatus(StatusLancamento.PENDENTE);
    
    Mockito.doNothing().when(service).validar(lancamentoSalvo);

    Mockito.when(repository.save(lancamentoSalvo)).thenReturn(lancamentoSalvo);
    
    //execucao
    service.atualizar(lancamentoSalvo);
    
    //verificacao
    Mockito.verify(repository, Mockito.times(1)).save(lancamentoSalvo);
  }
  
  @Test
  public void deveLancarErrorAoTentarAtualizarLancamentoQueAindaNaoFoiSalvo() {
    Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
    
    Assertions.catchThrowableOfType(() -> service.atualizar(lancamentoASalvar), NullPointerException.class);
    Mockito.verify(repository, Mockito.never()).save(lancamentoASalvar);
  }
  
  @Test
  public void deveDeletarUmLancamento() {
    //cenario 
    Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();
    lancamento.setId(1l);
    
    //execucao
    service.deletar(lancamento);
    
    //verificacao
    Mockito.verify(repository).delete(lancamento);
  }
  
  @Test
  public void deveLancarErroAoTentarDeletarUmLancamentoQueAindaNaoFoiSalvo() {
    //cenario 
    Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();
    
    //execucao
    Assertions.catchThrowableOfType(() -> service.deletar(lancamento), NullPointerException.class);
    
    //verificacao
    Mockito.verify(repository, Mockito.never()).delete(lancamento);
  }
  
}
