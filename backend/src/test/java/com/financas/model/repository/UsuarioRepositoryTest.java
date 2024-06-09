package com.financas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import com.financas.model.entity.Usuario;

@SpringBootTest
@Profile("test")
public class UsuarioRepositoryTest {
    
    @Autowired
    UsuarioRepository repository;

    @Test
    public void verificarExistenciaEmail() {
        //cenario
        Usuario usuario = new Usuario(null, "teste", "teste@gmail.com", "123");
        repository.save(usuario);
        //acao
        boolean result = repository.existsByEmail("teste@gmail.com");
        //verificacao
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverEmail() {
        //cenario
        repository.deleteAll();

        //acao
        boolean result = repository.existsByEmail("teste@gmail.com");

        //verificao
        Assertions.assertThat(result).isFalse();
    }
}
