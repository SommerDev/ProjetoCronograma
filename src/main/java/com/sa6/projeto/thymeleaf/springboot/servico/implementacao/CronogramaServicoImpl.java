package com.sa6.projeto.thymeleaf.springboot.servico.implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sa6.projeto.thymeleaf.springboot.excecao.CronogramaNaoEncontradaExcecao;
import com.sa6.projeto.thymeleaf.springboot.modelo.Cronograma;
import com.sa6.projeto.thymeleaf.springboot.repositorio.CronogramaRepositorio;
import com.sa6.projeto.thymeleaf.springboot.servico.ICronogramaServico;
 
@Service
public class CronogramaServicoImpl implements ICronogramaServico {
	@Autowired
	private CronogramaRepositorio repositorio;
 
	@Override
	public Cronograma salvarCronograma(Cronograma cronograma) {
		return repositorio.save(cronograma);
	}
 
	@Override
	public List<Cronograma> buscarTodosCronogramas() {
		return repositorio.findAll();
	}
 
	@Override
	public Cronograma buscarCronogramaPorId(Long codigo) {
		Optional<Cronograma> opcional = repositorio.findById(codigo); 
		if (opcional.isPresent()) {
			return opcional.get();
		} else {
			throw new CronogramaNaoEncontradaExcecao("Projeto com codigo: " + codigo + " não encontrado.");
		}
	}
 
	@Override
	public void deletarCronogramaPorId(Long codigo) {
		repositorio.delete(buscarCronogramaPorId(codigo));
	}
 
	@Override
	public void atualizarCronograma(Cronograma invoice) {
		repositorio.save(invoice);
	}
}
