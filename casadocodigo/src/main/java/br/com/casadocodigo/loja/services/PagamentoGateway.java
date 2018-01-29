package br.com.casadocodigo.loja.services;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.casadocodigo.loja.models.Pagamento;

public class PagamentoGateway implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String paga(BigDecimal total) {
		if(total.compareTo(new BigDecimal(500))>0) {
			total = new BigDecimal(500);
		}
		return ClientBuilder.newClient()
		.target("http://book-payment.herokuapp.com/payment")
		.request()
		.post(Entity.json(new Pagamento(total)), String.class);
	}

}
