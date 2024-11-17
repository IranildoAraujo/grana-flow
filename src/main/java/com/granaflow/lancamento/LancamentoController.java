package com.granaflow.lancamento;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/lancamentos")
@RestController
public class LancamentoController {

	private final LancamentoRepository lancamentoRepository;

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody LancamentoRequest entity) {
		var lancamento = lancamentoRepository.save(entity.toModel());
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}

	@GetMapping
	public List<LancamentoResponse> buscarTodos() {
		return lancamentoRepository.findAll().stream().map(Lancamento::toResponse).toList();
	}

}
