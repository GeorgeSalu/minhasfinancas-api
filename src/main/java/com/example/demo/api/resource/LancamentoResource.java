package com.example.demo.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.dto.LancamentoDTO;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Lancamento;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.enums.StatusLancamento;
import com.example.demo.model.enums.TipoLancamento;
import com.example.demo.service.LancamentoService;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {
  
  private LancamentoService service;
  private UsuarioService usuarioService;
  
  public LancamentoResource(LancamentoService service) {
    this.service = service;
  }
  
  @PostMapping
  public ResponseEntity salvar(@ResponseBody LancamentoDTO dto) {
    
  }
  
  private Lancamento converter(LancamentoDTO dto) {
    Lancamento lancamento = new Lancamento();
    lancamento.setId(dto.getId());
    lancamento.setDescricao(dto.getDescricao());
    lancamento.setAno(dto.getAno());
    lancamento.setMes(dto.getMes());
    lancamento.setValor(dto.getValor());
    
    Usuario usuario = usuarioService.obterPorId(dto.getUsuario()).orElseThrow(() -> new RegraNegocioException("usuario nao encontrado para o id informado"));
    
    lancamento.setUsuario(usuario);
    lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
    lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
    
    return lancamento;
  }

}
