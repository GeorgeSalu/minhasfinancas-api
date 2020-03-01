package com.example.demo.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.dto.LancamentoDTO;
import com.example.demo.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {
  
  private LancamentoService service;
  
  public LancamentoResource(LancamentoService service) {
    this.service = service;
  }
  
  @PostMapping
  public ResponseEntity salvar(@ResponseBody LancamentoDTO dto) {
    
  }

}
