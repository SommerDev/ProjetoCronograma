package com.sa6.projeto.thymeleaf.springboot.excecao;

public class CronogramaNaoEncontradaExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CronogramaNaoEncontradaExcecao() {
		super();
	}

	public CronogramaNaoEncontradaExcecao(String mensagemPersonalizada) {
		super(mensagemPersonalizada);
	}
}