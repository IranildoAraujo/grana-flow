package com.granaflow.lancamento;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoResponse {

	private Long id;

	private BigDecimal valorCusto;

	@Enumerated(EnumType.STRING)
	private EnumTipoCusto tipoCusto;
	
	private Date ultimoCustoRegistrado;

}
