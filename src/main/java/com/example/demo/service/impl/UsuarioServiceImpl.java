package com.example.demo.service.impl;

import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

  private UsuarioRepository repository;

  @Autowired
  public UsuarioServiceImpl(UsuarioRepository repository) {
    this.repository = repository;
  }

  @Override
  public Usuario autenticar(String email, String senha) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Usuario salvarUsuario(Usuario usuario) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void validarEmail(String email) {
    // TODO Auto-generated method stub

  }

}
