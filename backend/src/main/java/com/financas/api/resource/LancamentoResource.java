package com.financas.api.resource;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.financas.api.dto.LancamentoDTO;
import com.financas.exception.RegraNegocioException;
import com.financas.model.entity.Lancamento;
import com.financas.model.entity.Usuario;
import com.financas.model.entity.enums.StatusLancamento;
import com.financas.model.entity.enums.TipoLancamento;
import com.financas.service.LancamentoService;
import com.financas.service.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {
    
    @Autowired
    private LancamentoService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
	public ResponseEntity<?> buscar(@RequestParam(value ="descricao" , required = false) String descricao, @RequestParam(value = "mes", required = false) Integer mes, @RequestParam(value = "ano", required = false) Integer ano, @RequestParam("usuario") Long idUsuario, @RequestParam(value = "id", required = false) Long id) {
		
		Lancamento lancamentoFiltro = new Lancamento();
		lancamentoFiltro.setDescricao(descricao);
		lancamentoFiltro.setMes(mes);
		lancamentoFiltro.setAno(ano);
		
		Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
		if(!usuario.isPresent())
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Usuário não encontrado para o Id informado.");
		else
			lancamentoFiltro.setUsuario(usuario.get());
		
		List<Lancamento> lancamentos = service.buscar(lancamentoFiltro);
		return ResponseEntity.ok(lancamentos);
	}

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody LancamentoDTO dto) {
        try {
            Lancamento entidade = converter(dto);
            entidade = service.salvar(entidade);
            return new ResponseEntity<Lancamento>(entidade, HttpStatus.CREATED);
        } catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto) {
        service.obterPorId(id).map(entity -> {
            try {
                Lancamento lancamento = converter(dto);
                lancamento.setId(entity.getId());
                service.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);
            } catch(RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity<>("Lancamento nao encontrado na base de dados", HttpStatus.BAD_REQUEST));
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        return service.obterPorId(id).map(entidade -> {
            service.deletar(entidade);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>("Lancamento nao encontrado na base de dados", HttpStatus.BAD_REQUEST));
    }

 	private Lancamento converter(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = usuarioService
			.obterPorId(dto.getUsuario())
			.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado.") );
		
		lancamento.setUsuario(usuario);

		if(dto.getTipo() != null)
			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		
		if(dto.getStatus() != null)
			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		
		return lancamento;
	}
}
