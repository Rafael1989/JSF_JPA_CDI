package br.com.casadocodigo.loja.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Compra;

public class CompraDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public void salva(Compra compra) {
		em.persist(compra);
	}
	
	public Compra buscaPorUuid(String uuid) {
		return em.createQuery("select c from Compra c where c.uuid = :uuid",Compra.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
	}
}
