package com.example.demo.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.exception.ErroAutenticacao;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

  @SpyBean
  UsuarioServiceImpl service;
  
  @MockBean
  UsuarioRepository repository;
  
  @Test(expected = Test.None.class)
  public void deveSalvarUmUsuario() {
    //cenario
    Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
    Usuario usuario = Usuario.builder().id(1l).nome("nome").email("email@email.com").build();
    
    Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
    
    //acao
    Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
    
    //verificacao
    Assertions.assertThat(usuarioSalvo).isNotNull();
    Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
    Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
  }
  
  @Test(expected = Test.None.class)
  public void deveAutenticarUmUsuarioComSucesso() {
    //cenario
    String email = "email@email.com";
    String senha = "senha";
    
    Usuario usuario = Usuario.builder().email(email).senha(senha).build();
    Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

    //acao
    Usuario result = service.autenticar(email, senha);
    
    //verificacao
    Assertions.assertThat(result).isNotNull();
  
  }
  
  @Test(expected = ErroAutenticacao.class)
  public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
    //cenario
    Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
    
    //acao
    service.autenticar("email@email.com", "senha");
  }
  
  @Test
  public void deveLancarErroQuandoSenhaNaoBater() {
    //cenario
    String senha = "senha";
    Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();
    Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
    
    //acao
    Throwable exeption = Assertions.catchThrowable(() -> service.autenticar("email@email.com", "123"));
    Assertions.assertThat(exeption).isInstanceOf(ErroAutenticacao.class).hasMessage("senha invalida");
    
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
