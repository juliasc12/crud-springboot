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

import com.desafioapp.models.Produto;
import com.desafioapp.models.Pedido;
import com.desafioapp.repository.ProdutoRepository;
import com.desafioapp.repository.PedidoRepository;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoRepository er;
	
	@Autowired
	private ProdutoRepository cr;
	
	@RequestMapping(value="/cadastrarPedido", method=RequestMethod.GET)
	public String form(){
		return "pedido/formPedido";
	}
	
	@RequestMapping(value="/cadastrarPedido", method=RequestMethod.POST)
	public String form(@Validated Pedido pedido, BindingResult result, RedirectAttributes attributes){		
		er.save(pedido);
		attributes.addFlashAttribute("mensagem", "Pedido cadastrado com sucesso!");
		return "redirect:/cadastrarPedido";
	}
	
	@RequestMapping("/pedidos")
	public ModelAndView listaPedidos(){
		ModelAndView mv = new ModelAndView("listaPedidos");
		Iterable<Pedido> pedidos = er.findAll();
		mv.addObject("pedidos", pedidos);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesPedido(@PathVariable("id") long id){
		Pedido pedido = er.findById(id);
		ModelAndView mv = new ModelAndView("pedido/detalhesPedido");
		mv.addObject("pedido", pedido);
		
		Iterable<Produto> produtos = cr.findByPedido(pedido);
		mv.addObject("produtos", produtos);
		
		return mv;
	}
	
	@GetMapping("/deletarPedido/{id}")
	public String deletarPedido(@PathVariable long id){
		Pedido pedido = er.findById(id);
		er.delete(pedido);
		return "redirect:/pedidos";
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String detalhesPedidoPost(@PathVariable("id") long id, @Validated Produto produto,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id}";
		}
		Pedido pedido = er.findById(id);
		produto.setPedido(pedido);
		cr.save(produto);
		attributes.addFlashAttribute("mensagem", "Produto adicionado com sucesso!");
		return "redirect:/{id}";
	}
	
	@GetMapping("/deletarProduto/{nome}")
	public String deletarProduto(@PathVariable String nome){
		Produto produto = cr.findByNome(nome);
		cr.delete(produto);
		
		Pedido pedido = produto.getPedido();
		long codigoLong = pedido.getId();
		String id = "" + codigoLong;
		return "redirect:/" + id;
	}
	
	
	@GetMapping("/editarPedido/{id}")
	public ModelAndView editarPedido(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("pedido/editarPedido");
		Pedido pedido = er.findById(id);
		mv.addObject("pedido", pedido);
		return mv;
	}
	 
	@PostMapping("saveEditPedido")
	public String saveEditPedido(@Validated Pedido pedido,BindingResult result, RedirectAttributes attributes) {
		er.save(pedido);
		attributes.addFlashAttribute("mensagem", "Pedido editado com sucesso");
		return "redirect:/pedidos";
		
	}
 	
}

