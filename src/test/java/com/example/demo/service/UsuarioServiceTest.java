package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

  @Autowired
  UsuarioService service;
  
  @Autowired
  UsuarioRepository repository;
  
  @Test(expected = Test.None.class)
  public void deveValidarEmail() {
    //cenario
    repository.deleteAll();
    
    //acao
    service.validarEmail("email@email.com");
  }
  
  @Test(expected = RegraNegocioException.class)
  public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
    //cenario
    Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
    repository.save(usuario);
    
    //acao
    service.validarEmail("email@email.com");
    
  }
  
}
