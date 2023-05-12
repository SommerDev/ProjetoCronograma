package com.sa6.projeto.thymeleaf.springboot.servico;

import java.util.List;

import com.sa6.projeto.thymeleaf.springboot.modelo.Cronograma;

public interface ICronogramaServico {

	public Cronograma salvarCronograma(Cronograma cronograma);
	
	public List<Cronograma> buscarTodosCronogramas();
	
	public Cronograma buscarCronogramaPorId(Long codigo);
	
	public void deletarCronogramaPorId(Long codigo);
	
	public void atualizarCronograma(Cronograma cronograma);

	
}
