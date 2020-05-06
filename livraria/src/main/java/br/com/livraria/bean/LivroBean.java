package br.com.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.livraria.dao.DAO;
import br.com.livraria.model.Autor;
import br.com.livraria.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	private Livro livro = new Livro();
	private int autorId;

	public LivroBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Autor> getAutoresDoLivro() {
		return getLivro().getAutores();
		
	}
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscarPorId(getAutorId());
		getLivro().adicionarAutor(autor);
		List<Autor> autores = getLivro().getAutores();
		for (Autor autor2 : autores) {
			System.out.println(autor2.getNome() + " adicionado na lista!");
		}
	}
	public int getAutorId() {
		return autorId;
	}


	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}


	public List<Autor> getAutores() {
		List<Autor> listarTodosAutores = new DAO<Autor>(Autor.class).listarTodos();
		return listarTodosAutores;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void gravar() {
		System.out.println("Livro " + getLivro().getTitulo() + " gravado com sucesso!");
		
		if (getLivro().getAutores().isEmpty()) {
		//throw new RuntimeException("Livro deve ter pelo menos um autor!");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor!"));
		}
		
		new DAO<Livro>(Livro.class).adicionar(getLivro());
		setLivro(new Livro());
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com o número 1!"));
		}
	}
	
	public List<Livro> getLivros(){
		List<Livro> listarTodosLivros = new DAO<Livro>(Livro.class).listarTodos();
		return listarTodosLivros;
	}
	
	public void excluirLivro(int id) {
		Livro livroExcluir = getLivro();
		System.out.println("Livro: " + livroExcluir.getTitulo() + " excluido com sucesso!");
		//new DAO<Livro>(Livro.class).remover(livroExcluir.getId());
		
		//return "livro?faces-redirect=true";
	}
	
	public String formAutor() {
		System.out.println("Chamando o formulário do Autor");
		return "autor?faces-redirect=true";
	}
}
