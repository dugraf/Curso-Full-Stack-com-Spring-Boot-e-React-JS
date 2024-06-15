package com.financas.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import com.financas.exception.RegraNegocioException;
import com.financas.model.entity.Lancamento;
import com.financas.model.entity.enums.StatusLancamento;
import com.financas.model.entity.enums.TipoLancamento;
import com.financas.model.repository.LancamentoRepository;
import com.financas.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Override
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        return repository.save(lancamento);
    }

    @Override
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        validar(lancamento);
        return repository.save(lancamento);
    }

    @Override
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Override
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example<Lancamento> example = Example.of(lancamentoFiltro, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatus(status);
        atualizar(lancamento);
    }

    @Override
    public void validar(Lancamento lancamento) {
        if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals(""))
            throw new RegraNegocioException("Informe uma descricao valida!");
        if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12)
            throw new RegraNegocioException("Informe um mes valido!");
        if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4)
            throw new RegraNegocioException("Informe um ano valido!");
        if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null)
            throw new RegraNegocioException("Informe um usuario!");
        if(lancamento.getValor() == null || lancamento.getValor() < 0)
            throw new RegraNegocioException("Informe um valor valido!");
        if(lancamento.getTipo() == null)
            throw new RegraNegocioException("Informe um tipo de lancamento!");
    }

    @Override
    public Optional<Lancamento> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Double obterSaldoPorUsuario(Long id) {
        Double receitas = repository.obterSaldo(id/*, TipoLancamento.RECEITA, StatusLancamento.EFETIVADO*/);
        Double despesas = repository.obterSaldo(id/*, TipoLancamento.DESPESA, StatusLancamento.EFETIVADO*/);

        if(receitas == null)
            receitas = 0.0;
        if(despesas == null)
            despesas = 0.0;
        
        return (receitas - despesas);
    }
}
