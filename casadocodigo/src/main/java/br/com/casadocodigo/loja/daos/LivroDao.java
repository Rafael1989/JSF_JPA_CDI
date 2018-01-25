package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salva(Livro livro) {
		em.persist(livro);
	}

}
