package com.example.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

  UsuarioService service;
  
  @MockBean
  UsuarioRepository repository;
  
  @Before
  public void setup() {
    service = new UsuarioServiceImpl(repository);
  }
  
  @Test(expected = Test.None.class)
  public void deveValidarEmail() {
    //cenario
    Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
    
    repository.deleteAll();
    
    //acao
    service.validarEmail("email@email.com");
  }
  
  @Test(expected = RegraNegocioException.class)
  public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
    //cenario
    Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

    //acao
    service.validarEmail("email@email.com");
    
  }
  
}
