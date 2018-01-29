package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.jpa.QueryHints;

import br.com.casadocodigo.loja.models.Livro;
@Stateful
public class LivroDao {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public void salva(Livro livro) {
		em.persist(livro);
	}
	
	public List<Livro> getLivros(){
		return em.createQuery("select l from Livro l join fetch l.autores",Livro.class)
				.getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		return em.createQuery("select l from Livro l order by l.dataPublicacao desc",Livro.class)
				.setMaxResults(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<Livro> demaisLivros() {
		return em.createQuery("select l from Livro l order by l.dataPublicacao desc", Livro.class)
				.setFirstResult(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public Livro buscaPorId(Integer id) {
		try {
			return em.createQuery("select l from Livro l join fetch l.autores where l.id = :id",Livro.class)
					.setParameter("id", id)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

}
