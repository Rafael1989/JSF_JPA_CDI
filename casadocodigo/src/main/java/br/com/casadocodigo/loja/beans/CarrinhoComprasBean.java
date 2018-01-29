package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.ItemCompra;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class CarrinhoComprasBean {

	@Inject
	private LivroDao livroDao;
	
	@Inject
	private CarrinhoCompras carrinhoCompras;
	
	public String add(Integer id) {
		Livro livro = livroDao.buscaPorId(id);
		ItemCompra itemCompra = new ItemCompra(livro);
		carrinhoCompras.add(itemCompra);
		return "carrinho?faces-redirect=true";
	}
	
	public List<ItemCompra> getItens(){
		return carrinhoCompras.getItens();
	}
	
	public void remove(ItemCompra item) {
		this.carrinhoCompras.remove(item);
	}
}
