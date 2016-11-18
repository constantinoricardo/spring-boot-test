package br.com.organizacao.estudospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

	@Autowired
	private MailSender mailSender;
	
	private SimpleMailMessage templateMessage;
	
	@Value("${send.from.email}")
	private String fromEmail;
	
	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public String send(
			@RequestParam(value="assunto") String assunto,
			@RequestParam(value="para") String para,
			@RequestParam(value="mensagem") String mensagem
			) {
		
		this.templateMessage = new SimpleMailMessage();
		
		this.templateMessage.setSubject(assunto);
		this.templateMessage.setFrom(fromEmail);
		this.templateMessage.setTo(para);
		
		SimpleMailMessage message = new SimpleMailMessage(this.templateMessage);
		message.setText(mensagem);

		try {
			
			this.mailSender.send(message);
			
		} catch (MailException ex) {
			return "Ocorreu um erro no envio de email " + ex.getMessage();
		}
		return "Email enviado para " + para + ", com sucesso!!!";
	}
	
}
