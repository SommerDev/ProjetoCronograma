package com.sa6.projeto.thymeleaf.springboot.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sa6.projeto.thymeleaf.springboot.excecao.CronogramaNaoEncontradaExcecao;
import com.sa6.projeto.thymeleaf.springboot.modelo.Cronograma;
import com.sa6.projeto.thymeleaf.springboot.servico.ICronogramaServico;


@Controller
@RequestMapping("/cronograma")
public class CronogramaControlador {
	
	@Autowired
	private ICronogramaServico service;

	@GetMapping("/")
	public String exibirPaginaInicial() {
		return "paginaInicio";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicao() {
		return "adicionarCronograma";
	}

	@PostMapping("/salvar")
	public String salvarCronograma(@ModelAttribute Cronograma cronograma, Model modelo) {
		service.salvarCronograma(cronograma);
		Long codigo = service.salvarCronograma(cronograma).getCodigo();
		String mensagem = "Projeto com codigo: '" + codigo + "' salva com sucesso!";
		modelo.addAttribute("message", mensagem);
		return "adicionarCronograma";
	}

	@GetMapping("/listar")
	public String buscarTodosCronogramas(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
		List<Cronograma> cronogramas = service.buscarTodosCronogramas();
		modelo.addAttribute("listagem", cronogramas);
		modelo.addAttribute("message", mensagem);
		return "listarCronograma";
	}

	@GetMapping("/editar")
	public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long codigo) {
		String pagina = null;
		try {
			Cronograma cronograma = service.buscarCronogramaPorId(codigo);
			modelo.addAttribute("cronograma", cronograma);
			pagina = "editarCronograma";
		} catch (CronogramaNaoEncontradaExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
			pagina = "redirect:listar";
		}
		return pagina;
	}

	@PostMapping("/atualizar")
	public String atualizarCronograma(@ModelAttribute Cronograma cronograma, RedirectAttributes atributos) {
		service.atualizarCronograma(cronograma);
		Long codigo = cronograma.getCodigo();
		atributos.addAttribute("message", "Projeto com codigo: '" + codigo + "' atualizada com sucesso!");
		return "redirect:listar";
	}

	@GetMapping("/deletar")
	public String deletarCronograma(@RequestParam Long codigo, RedirectAttributes atributos) {
		try {
			service.deletarCronogramaPorId(codigo);
			atributos.addAttribute("message", "Projeto com codigo: '" + codigo + "' excluída com sucesso!");
		} catch (CronogramaNaoEncontradaExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
		}
		return "redirect:listar";
	}
	
	
}
