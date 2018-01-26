package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Named
public class AdminListaLivrosBean {

	@Inject
	private LivroDao livroDao;
	
	public List<Livro> getLivros(){
		return livroDao.getLivros();
	}
	
	
}
