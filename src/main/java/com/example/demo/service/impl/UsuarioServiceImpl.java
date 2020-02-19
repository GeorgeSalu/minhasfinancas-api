package com.example.demo.service.impl;

import com.example.demo.exception.ErroAutenticacao;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

  private UsuarioRepository repository;

  public UsuarioServiceImpl(UsuarioRepository repository) {
    this.repository = repository;
  }

  @Override
  public Usuario autenticar(String email, String senha) {
    Optional<Usuario> usuario = repository.findByEmail(email);
    if(!usuario.isPresent()) {
      throw new ErroAutenticacao("Usuario nao encontrado para o email informado");
    }
    if(!usuario.get().getSenha().equals(senha)) {
      throw new ErroAutenticacao("senha invalida");
    }
    return usuario.get();
  }

  @Override
  @Transactional
  public Usuario salvarUsuario(Usuario usuario) {
    validarEmail(usuario.getEmail());
    return repository.save(usuario);
  }

  @Override
  public void validarEmail(String email) {
    boolean existe = repository.existsByEmail(email);
    if(existe) {
      throw new RegraNegocioException("Ja existe um usuario cadastrado com este email");
    }
  }

}
