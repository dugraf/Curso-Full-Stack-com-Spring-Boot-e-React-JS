package com.financas.api.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoDTO {
    private Long id;
    private String descricao;
    private Integer mes, ano;
    private BigDecimal valor;
    private Long usuario;
    private String tipo, status;
}
