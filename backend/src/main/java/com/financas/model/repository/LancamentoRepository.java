package com.financas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financas.model.entity.Lancamento;
import com.financas.model.entity.enums.StatusLancamento;
import com.financas.model.entity.enums.TipoLancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    // @Query(value="SELECT SUM(l.valor) FROM lancamento l JOIN usuario u ON l.id_usuario = u.id WHERE u.id = 4 AND l.tipo = 'RECEITA' GROUP BY u.id")
    // Double obterSaldo(@Param("idUsuario") Long idUsuario, @Param("tipo") TipoLancamento tipo, @Param("status") StatusLancamento status);
}
