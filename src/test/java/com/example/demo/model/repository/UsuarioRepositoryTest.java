package com.example.demo.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.entity.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {
  
  @Autowired
  UsuarioRepository repository;
  
  @Autowired
  TestEntityManager entityManager;
  
  @Test
  public void deveVerificarAExistenciaDeUmEmail() {
    //cenario
    Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
    entityManager.persist(usuario);
    
    //acao
    boolean result = repository.existsByEmail("usuario@email.com");
    
    //verificacao
    Assertions.assertThat(result).isTrue();
  }
  
  @Test
  public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
    
    //acao
    boolean result = repository.existsByEmail("usuario@email.com");
    
    //verificacao
    Assertions.assertThat(result).isFalse();
  }
  
  @Test
  public void devePersistirUmUsuarioNaBaseDeDados() {
    //cenario
    Usuario usuario = Usuario
                        .builder()
                        .nome("usuario")
                        .email("usuario@email.com")
                        .senha("senha")
                        .build();
    
    //acao
    Usuario usuarioSalvo = repository.save(usuario);
    
    Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
  }
  
  @Test
  public void deveBuscarUmUsuarioPorEmail() {
    //cenario
    Usuario usuario = criarUsuario();
    entityManager.persist(usuario);
    
    //verificacao
    Optional<Usuario> result = repository.findByEmail("usuario@email.com");
    
    Assertions.assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void deveRetornarVazioUsuarioPorEmailQuandoNaoExistirNaBase() {
    //cenario
    
    //verificacao
    Optional<Usuario> result = repository.findByEmail("usuario@email.com");
    
    Assertions.assertThat(result.isPresent()).isFalse();
  }
  
  public static Usuario criarUsuario() {
    return Usuario
                .builder()
                .nome("usuario")
                .email("usuario@email.com")
                .senha("senha")
                .build();

  }
  
}
