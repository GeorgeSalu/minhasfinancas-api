package com.example.demo.service.impl;

import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
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
    // TODO Auto-generated method stub
    return null;
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
