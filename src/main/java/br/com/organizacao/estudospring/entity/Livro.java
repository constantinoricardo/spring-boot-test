package br.com.organizacao.estudospring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="livro")
public class Livro {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="titulo", nullable=false)
	@NotNull
	@Max(20)
	@Min(3)
	private String titulo;
	
	@Column(name="isbn", nullable=false, unique=true)
	@NotNull
	@Max(20)
	@Min(3)
	private String isbn;
	
	@Column(name="autor", nullable=false)
	@NotNull
	@Max(64)
	@Min(3)
	private String autor;
	
	@Column(name="description", nullable=true)
	@NotNull
	@Min(3)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
