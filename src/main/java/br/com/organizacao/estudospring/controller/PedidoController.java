package br.com.organizacao.estudospring.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.organizacao.estudospring.entity.Livro;

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
		URL url;
		StringBuffer response = null;
		
		Livro livro = new Livro();
		livro.setAutor("Ricardo Constantino");
		livro.setDescription("Livro de Teologia florence");
		livro.setIsbn("98282882");
		livro.setTitulo("TEste livro");
		
		try {		
									
			url = new URL("http://172.16.16.140:122/diretiva/projeto_teste/value.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();			
//			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);  
            
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    		wr.writeBytes(livro.toString());
    		wr.flush();
    		wr.close();
    		
//    		System.out.println(conn.getResponseCode());

            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(
	    		        new InputStreamReader(conn.getInputStream()));
	    		String inputLine;
	    		response = new StringBuffer();
	
	    		while ((inputLine = in.readLine()) != null) {
	    			response.append(inputLine);
	    		}
	    		in.close();
            }    		
            
            conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return response.toString();
	}
	
}
