package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.casadocodigo.loja.daos.CompraDao;
@Named
@SessionScoped
public class CarrinhoCompras implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Set<ItemCompra> itens = new HashSet<>();
	
	@Inject
	private CompraDao compraDao;
	
	public void add(ItemCompra item) {
		itens.add(item);
	}
	
	public List<ItemCompra> getItens(){
		return new ArrayList<>(itens);
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (ItemCompra itemCompra : itens) {
			total = total.add(itemCompra.getLivro().getPreco().multiply(new BigDecimal(itemCompra.getQuantidade())));
		}
		return total;
	}
	
	public BigDecimal getTotal(ItemCompra item) {
		return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	}
	
	public void remove(ItemCompra item) {
		this.itens.remove(item);
	}
	
	public Integer getQuantidadeTotal() {
		return this.itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}
	
	public void finaliza(Usuario usuario) {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		compra.setItens(toJson());
		compraDao.salva(compra);
	}

	private String toJson() {
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		for (ItemCompra item : itens) {
			jsonArray.add(Json.createObjectBuilder()
					.add("titulo", item.getLivro().getTitulo())
					.add("preco", item.getLivro().getPreco())
					.add("quantidade", item.getQuantidade())
					.add("total", getTotal(item))
			);
		}
		return jsonArray.build().toString();
	}

}
