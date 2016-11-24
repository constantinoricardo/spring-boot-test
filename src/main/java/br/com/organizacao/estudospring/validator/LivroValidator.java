package br.com.organizacao.estudospring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.organizacao.estudospring.entity.Livro;

public class LivroValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Livro.class.equals(classe);
	}
	
	public void validateOtherMethods(Object objeto, Errors error) {
		
		try {
		
			Livro livro = (Livro) objeto;		
			
			if (livro.getAutor().length() < 3 || livro.getAutor().length() > 50) 
				error.rejectValue("autor", "betweenValues", "Número caracteres de Autor entre 3 e 50.");
			
			if (livro.getTitulo().length() < 3 || livro.getTitulo().length() > 50) 
				error.rejectValue("titulo", "betweenValues", "Número caracteres de Titulo entre 3 e 50.");
					
			if (livro.getIsbn().length() < 3 || livro.getTitulo().length() > 50) 
				error.rejectValue("isbn", "betweenValues", "Número caracteres de ISBN entre 3 e 50.");
					
			if (livro.getDescription().length() < 3 || livro.getTitulo().length() > 50) 
				error.rejectValue("descricao", "betweenValues", "Número caracteres de Descrição entre 3 e 50.");
		
		} catch (NullPointerException ex) {
			ex.getStackTrace();
		}
		
	}

	@Override
	public void validate(Object objeto, Errors error) {
		ValidationUtils.rejectIfEmpty(error, "titulo", "required", "Por favor, informe o Titulo.");
		ValidationUtils.rejectIfEmpty(error, "autor", "required", "Por favor, informe o Autor.");
		ValidationUtils.rejectIfEmpty(error, "isbn", "required", "Por favor, informe o ISBN.");
		ValidationUtils.rejectIfEmpty(error, "description", "required", "Por favor, informe a Descrição.");
		
		this.validateOtherMethods(objeto, error);
			
	}

}
