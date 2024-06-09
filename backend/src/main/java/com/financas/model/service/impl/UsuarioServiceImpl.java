package com.financas.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.financas.model.entity.Usuario;
import com.financas.model.entity.exception.RegraNegocioException;
import com.financas.model.repository.UsuarioRepository;
import com.financas.model.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario autenticar(String email, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public void validarEmail(String email) {
        if(repository.existsByEmail(email))
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email!");
    }
    
}
