package br.com.organizacao.estudospring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@RestController
public class PedidoController {

	@RequestMapping(value="/pedido/servico", method=RequestMethod.POST)
	public String servico() {
		String objeto = "{nome: 'Ricardo Constantino', profissao: 'Diretor', idade: '31'}";
		JSONObject json = new JSONObject(objeto);
		
		String nome = json.getString("nome");
		String profissao = json.getString("profissao");
		String idade = json.getString("idade");
		
		return "Nome: " + nome + " Profissao: " + profissao + " Idade: " + idade;		
	}		
		
	@RequestMapping(value="/pedido/webservice", method=RequestMethod.POST)
	public String webservice() {
		String leitor = "";
		
		try {
			
			URL url = new URL("http://argentumws.caelum.com.br/negociacoes");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));			
			
			while (in.readLine() != null) {
				leitor += in.readLine().trim();
			}
			in.close();
									
			return leitor;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/pedido/criar", method=RequestMethod.POST)
	public String criar() {
		JSONObject json = new JSONObject();
		json.put("nome", "Teste");
		json.put("empresa", "NTK");
		
		return json.toString();
	}
	
}
