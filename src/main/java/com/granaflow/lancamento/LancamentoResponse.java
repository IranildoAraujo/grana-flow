package com.granaflow.lancamento;

import java.math.BigDecimal;

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
	
	//TODO: Ùtimas modificações: De: Date para: String
	//TODO: Nome: De: ultimoCustoRegistrado para dataRegistro
	//TODO: Formato: De: "2024-12-09T21:46:28" para "09/12/2024 15:30:45"
	private String dataRegistro;

}
