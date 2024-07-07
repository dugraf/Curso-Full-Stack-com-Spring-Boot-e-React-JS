package com.financas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.financas.model.entity.Lancamento;
import com.financas.model.entity.enums.StatusLancamento;
import com.financas.model.entity.enums.TipoLancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    // @Query("select sum(l.valor) from Lancamento l" + "where l.usuario.id = :idUsuario")
    // Double obterSaldo(@Param("idUsuario") Long idUsuario);
    
}
