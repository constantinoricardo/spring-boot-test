package br.com.organizacao.estudospring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.organizacao.estudospring.dao.LivroDAO;
import br.com.organizacao.estudospring.entity.Livro;

@Controller
public class LivroController {
	
	@Autowired
	private LivroDAO livroDao;
	
	@RequestMapping(value = "/all")
	@ResponseBody
	public Iterable<Livro> all() {
		Iterable<Livro> livros = this.livroDao.findAll();
		return livros;
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value="id") Long id) {
		try {
			
			this.livroDao.delete(id);
			
		} catch (Exception ex) {
			return "Erro " + ex.toString();
		}
		return "Book successfully removed";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(
			@RequestParam(value="id") Long id,
			@RequestParam(value="autor") String autor,
			@RequestParam(value="titulo") String titulo,
			@RequestParam(value="description") String description,
			@RequestParam(value="isbn") String isbn) {
		
		try {
			
			Livro livro = this.livroDao.findOne(id);
			livro.setAutor(autor);
			livro.setTitulo(titulo);
			livro.setDescription(description);
			livro.setIsbn(isbn);
			
			this.livroDao.save(livro);
			
		} catch (Exception ex) {
			return "Erro " + ex.toString();
		}
		
		return "Book successfully updated";
	}
	
	@RequestMapping(value = "/findByTitulo", method = RequestMethod.POST)
	@ResponseBody
	public Iterable<Livro> findByTitulo(@RequestParam(value="titulo") String titulo) {
		Iterable<Livro> livros = this.livroDao.findByTitulo(titulo);
		return livros;
	}
	
	@RequestMapping(value="/criarTeste", method=RequestMethod.POST)
	@ResponseBody
	public String criarTeste(
							@RequestParam(value="autor") String autor,
							@RequestParam(value="titulo") String titulo,
							@RequestParam(value="description") String description,
							@RequestParam(value="isbn") String isbn
							) {		
		
		return autor + " Cadastrado com sucesso.";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String create(
			@RequestParam(value="autor") String autor,
			@RequestParam(value="titulo") String titulo,
			@RequestParam(value="description") String description,
			@RequestParam(value="isbn") String isbn
			) {
		try {
		
			Livro livro = new Livro();
			livro.setAutor(autor);
			livro.setTitulo(titulo);
			livro.setDescription(description);
			livro.setIsbn(isbn);
			
			livroDao.save(livro);
			
		} catch (Exception ex) {
			return "Error "+ ex.toString();
		}
	    return "Book succesfully created";
	}
	
}
