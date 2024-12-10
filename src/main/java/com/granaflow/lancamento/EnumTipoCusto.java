package com.granaflow.lancamento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTipoCusto {

	VESTUARIO("Vestuário"), ALIMENTACAO("Alimentação"), COLEGIO("Colégio"), MOVEL("Móvel"), TECNOLOGIA("Tecnologia"),
	ENERGIA("Energia"), AGUA("Água"), ALUGUEL("Aluguel"), INTERNET("Internet"), ACADEMIA("Academia"),
	MOTORISTA_APP("Motorista de Aplicativo"), FARMACIA("Farmácia"), DENTISTA("Destista"),
	OFTALMOLOGISTA("Oftalmologista"), BRINQUEDO("Brinquedo"), OUTRO("Outro"), LIVRO("Livro");

	public String descricao;

}
