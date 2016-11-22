package br.com.organizacao.estudospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PedidoController {

	@RequestMapping(value="/pedido/servico")
	@ResponseBody
	public String servico() {
		return "Teste Servico";
	}
	
}
