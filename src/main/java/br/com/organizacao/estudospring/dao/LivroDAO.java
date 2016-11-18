package br.com.organizacao.estudospring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.organizacao.estudospring.entity.Livro;

@Transactional
public interface LivroDAO extends CrudRepository<Livro, Long> {

	public List<Livro> findByTitulo(String titulo);
}
