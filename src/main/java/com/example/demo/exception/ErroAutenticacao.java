package com.example.demo.exception;

public class ErroAutenticacao extends RuntimeException{

  public ErroAutenticacao(String mensagem) {
    super(mensagem);
  }
  
}
