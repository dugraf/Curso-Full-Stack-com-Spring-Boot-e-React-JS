package com.financas.api.resource;

import org.springframework.web.bind.annotation.RestController;
import com.financas.api.dto.UsuarioDTO;
import com.financas.exception.AutenticacaoException;
import com.financas.exception.RegraNegocioException;
import com.financas.model.entity.Usuario;
import com.financas.service.LancamentoService;
import com.financas.service.UsuarioService;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        } catch(AutenticacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), LocalDate.now());

        try {
            Usuario usuarioSalvo = service.salvar(usuario);
            return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
        } catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

	@GetMapping("{id}/saldo")
	public ResponseEntity<?> obterSaldo(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = service.obterPorId(id);
		
		if(!usuario.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Double saldo = lancamentoService.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
	}

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        Optional<Usuario> usuario = service.obterPorId(id);

        if(!usuario.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(usuario);
    }
}
