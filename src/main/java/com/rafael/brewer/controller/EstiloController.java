package com.rafael.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafael.brewer.model.Estilo;
import com.rafael.brewer.service.CadastroEstiloService;
import com.rafael.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping(value = "/estilos")
public class EstiloController {

	@Autowired
	private CadastroEstiloService cadastro;

	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		ModelAndView model = new ModelAndView("/estilo/CadastroEstilo");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			// model.addAttribute("mensagem", "Erro no formulário");
			return novo(estilo);
		}
		try {
			cadastro.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			// TODO: handle exception
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}

		redirectAttributes.addFlashAttribute("mensagem", "Estilo salvo com Sucesso!");
		System.out.println(" Nome: " + estilo.getNome());
		return new ModelAndView("redirect:/estilos/novo");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> cadastrarRapido(@RequestBody @Valid Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}

		estilo = cadastro.salvar(estilo);

		// System.out.println(">>>O estilo cadastrado foi "+estilo.getNome());
		return ResponseEntity.ok(estilo);
	}
}
