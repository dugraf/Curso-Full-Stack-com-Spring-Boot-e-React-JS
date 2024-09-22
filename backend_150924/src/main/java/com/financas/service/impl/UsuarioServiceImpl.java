package com.financas.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.exception.AutenticacaoException;
import com.financas.exception.RegraNegocioException;
import com.financas.model.entity.Usuario;
import com.financas.model.repository.UsuarioRepository;
import com.financas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        if(!usuario.isPresent())
            throw new AutenticacaoException("Usuário não encontrado!");
        if(!usuario.get().getSenha().equals(senha))
            throw new AutenticacaoException("Senha inválida!");
        
        return usuario.get();
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        if(repository.existsByEmail(email))
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email!");
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return repository.findById(id);
    }
    
}
