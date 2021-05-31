package com.desafioapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafioapp.models.Cliente;
import com.desafioapp.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clr;
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.GET)
	public String form(){
		return "cliente/formCliente";
	}
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.POST)
	public String form(@Validated Cliente cliente, BindingResult result, RedirectAttributes attributes){		
		clr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
		return "redirect:/cadastrarCliente";
	}
	
	@RequestMapping("/clientes")
	public ModelAndView listaClientes(){
		ModelAndView mv = new ModelAndView("listaClientes");
		Iterable<Cliente> clientes = clr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@GetMapping("/deletarCliente/{id}")
	public String deletarCliente(@PathVariable Long id){
		Cliente cliente = clr.findById(id);
		clr.delete(cliente);
		return "redirect:/clientes";
	}
	
	
	@GetMapping("/editarCliente/{id}")
	public ModelAndView editarCliente(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("cliente/editarCliente");
		Cliente cliente = clr.findById(id);
		mv.addObject("cliente", cliente);
		return mv;
	} 
	 
	
 	@PostMapping("/saveEdit")
	public String saveCliente(@Validated Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		clr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente editado com sucesso!");
		return "redirect:/clientes";
	}
	
	
}
