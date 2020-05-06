package br.com.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.DAO;
import br.com.livraria.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {
	
	private Autor autor = new Autor();


	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public String gravar() {
		if (getAutor().getNome().isEmpty()) {
			throw new RuntimeException("Favor cadastrar o nome do autor!");
		}
		
		new DAO<Autor>(Autor.class).adicionar(getAutor());
		System.out.println(getAutor().getNome() + " cadastrado com sucesso!");
		
		setAutor(new Autor());
		
		System.out.println("Novo Autor " + this.autor.getNome());
		
		return "livro?faces-redirect=true";
	}
	
	public List<Autor> getListarAutores(){
		List<Autor> listarTodos = new DAO<Autor>(Autor.class).listarTodos();
		return listarTodos;
	}
	
	public void remover(int id) {
		new DAO<Autor>(Autor.class).remover(id);
		
		System.out.println("Autor Excluido com sucesso!");
	}
	
	public Autor buscaPorId(int id) {
		Autor buscarPorId = new DAO<Autor>(Autor.class).buscarPorId(id);
		System.out.println("Autor: " + buscarPorId.getNome());
		return buscarPorId;
	}
	
}
