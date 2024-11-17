package com.granaflow.lancamento;

import java.math.BigDecimal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoRequest {

	private Long id;

	private BigDecimal valorCusto;

	@Enumerated(EnumType.STRING)
	private EnumTipoCusto tipoCusto;

	public Lancamento toModel() {
		return Lancamento.builder().id(id).valorCusto(valorCusto).tipoCusto(tipoCusto).build();
	}

}
