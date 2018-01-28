package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named
@SessionScoped
public class CarrinhoCompras implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Set<ItemCompra> itens = new HashSet<>();
	
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

}
