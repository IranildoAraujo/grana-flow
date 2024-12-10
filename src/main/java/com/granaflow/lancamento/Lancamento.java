package com.granaflow.lancamento;

import java.math.BigDecimal;
import java.util.Date;

import com.granaflow.util.FormatDateUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "LAN_LANCAMENTO", schema = "system")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LAN_LANCAMENTO")
	@SequenceGenerator(name = "SQ_LAN_LANCAMENTO", sequenceName = "SEQ_LAN_LANCAMENTO", allocationSize = 1)
	@Column(name = "LAN_ID")
	private Long id;

	@Column(name = "LAN_DTA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;

	@Column(name = "LAN_VLR_CUSTO")
	private BigDecimal valorCusto;

	@Enumerated(EnumType.STRING)
	@Column(name = "LAN_TIP_CUSTO")
	private EnumTipoCusto tipoCusto;

	@PrePersist
	public void prePersist() {
		this.dataRegistro = new Date();
	}

	public LancamentoResponse toResponse() {
		return LancamentoResponse.builder().id(id).dataRegistro(FormatDateUtil.getStringDataHora(dataRegistro))
				.valorCusto(valorCusto).tipoCusto(tipoCusto).build();
	}

}
