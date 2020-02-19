package com.example.demo.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.entity.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {
  
  @Autowired
  UsuarioRepository repository;
  
  @Test
  public void deveVerificarAExistenciaDeUmEmail() {
    //cenario
    Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
    repository.save(usuario);
    
    //acao
    boolean result = repository.existsByEmail("usuario@email.com");
    
    //verificacao
    Assertions.assertThat(result).isTrue();
  }
  
  @Test
  public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
    // cenario
    repository.deleteAll();
    
    //acao
    boolean result = repository.existsByEmail("usuario@email.com");
    
    //verificacao
    Assertions.assertThat(result).isFalse();
  }
  
}
