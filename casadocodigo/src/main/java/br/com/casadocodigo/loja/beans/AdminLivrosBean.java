package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao dao;
	
	@Inject
	private AutorDao autorDao;
	
	private List<Integer> autoresId = new ArrayList<>();
	
	public List<Integer> getAutoresId() {
		return autoresId;
	}
	
	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	@Transactional
	public void salva() {
		for(Integer autorId : autoresId) {
			Autor autor = new Autor(autorId);
			livro.addAutor(autor);
		}
		dao.salva(livro);
		
		this.livro = new Livro();
		this.autoresId = new ArrayList<>();
	}
	
	public List<Autor> getAutores(){
		return autorDao.getAutores();
	}

}
