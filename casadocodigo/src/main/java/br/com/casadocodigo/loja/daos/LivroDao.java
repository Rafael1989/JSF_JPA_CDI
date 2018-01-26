package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salva(Livro livro) {
		em.persist(livro);
	}
	
	public List<Livro> getLivros(){
		return em.createQuery("select l from Livro l join fetch l.autores",Livro.class).getResultList();
	}

}
