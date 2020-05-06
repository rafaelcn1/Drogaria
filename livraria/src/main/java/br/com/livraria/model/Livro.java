package br.com.livraria.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo, isbn;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataLacamento = Calendar.getInstance();
	private double preco;

	@ManyToMany
	private List<Autor> autores = new ArrayList<Autor>();

	public Livro() {

	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionarAutor(Autor autor) {
		this.autores.add(autor);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	public Calendar getDataLacamento() {
		return dataLacamento;
	}

	public void setDataLacamento(Calendar dataLacamento) {
		this.dataLacamento = dataLacamento;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
