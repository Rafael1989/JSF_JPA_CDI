package br.com.casadocodigo.loja.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao dao;
	
	@Inject
	private FacesContext context;
	
	@Inject
	private AutorDao autorDao;
	
	private List<Integer> autoresId = new ArrayList<>();
	
	private Part capaLivro;
	
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
	
	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

	@Transactional
	public String salva() throws IOException {
		dao.salva(livro);
		FileSaver fileSaver = new FileSaver();
		String relativePath = fileSaver.write(capaLivro, "rafael");
		livro.setCapaPath(relativePath);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("O livro foi salvo com sucesso"));
		
		return "/admin/livros/lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores(){
		return autorDao.getAutores();
	}

}
