package com.rafael.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafael.brewer.model.Cerveja;
import com.rafael.brewer.model.Origem;
import com.rafael.brewer.model.Sabor;
import com.rafael.brewer.repository.Estilos;
import com.rafael.brewer.service.CadastroCervejaService;

@Controller
@RequestMapping(value= "/cervejas")
public class CervejaController {

	private static final Logger logger = LoggerFactory.getLogger(CervejaController.class);

	/*
	 * @Autowired private Cervejas cervejas;
	 */
	@Autowired
	private Estilos estilos;
	@Autowired
	private CadastroCervejaService cadastro;

	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {

		/*
		 * Optional<Cerveja> optionalCerveja = cervejas.findBySku(""); if
		 * (optionalCerveja.isPresent()) { System.out.println("Contém uma cerveja"); }
		 * System.out.println(optionalCerveja.isPresent());
		 */
		System.out.println("Atualizou funcionou");
		ModelAndView modelAndView = new ModelAndView("/cerveja/CadastroCerveja");
		modelAndView.addObject("sabores", Sabor.values());
		modelAndView.addObject("estilos", estilos.findAll());
		modelAndView.addObject("origens", Origem.values());

		if (logger.isDebugEnabled()) {
			logger.info("Hello World Nivel Info!");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			//model.addAttribute("mensagem", "Erro no formulário");
			return novo(cerveja);
		}

		cadastro.salvar(cerveja);

		redirect.addFlashAttribute("mensagem", "Cerveja Salva com Sucesso!");
		System.out.println("SKU: " + cerveja.getSku() + " Nome: " + cerveja.getNome() + " Descrição: "
				+ cerveja.getDescricao() + " Sabor: " + cerveja.getSabor() + " Origem: " + cerveja.getOrigem()
				+ " Estilo: " + cerveja.getEstilo().getCodigo());
		return new ModelAndView("redirect:/cervejas/novo");
	}

}
