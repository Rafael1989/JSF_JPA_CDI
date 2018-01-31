package br.com.casadocodigo.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.models.Promo;
import br.com.casadocodigo.loja.websockets.PromosEndPoint;

@Model
public class AdminPromosBean {

	private Promo promo = new Promo();
	
	@Inject
	private PromosEndPoint promosEndPoint;
	
	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	
	public void envia() {
		promosEndPoint.send(promo);
	}
}