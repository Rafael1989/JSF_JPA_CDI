
package br.com.casadocodigo.loja.services;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.models.Compra;

@Path("/pagamento")
public class PagamentoService {
	
	@Inject
	private CompraDao compraDao;
	
	@Context
    ServletContext context;
	
	@Inject
	private PagamentoGateway pagamentoGateway;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(50);

	@POST
	public void paga(@Suspended AsyncResponse ar, @QueryParam("uuid") String uuid) {
		executor.submit(()->{
			try {
				Compra compra = compraDao.buscaPorUuid(uuid);
				pagamentoGateway.paga(compra.getTotal());
				URI uri = UriBuilder.fromPath("http://localhost:8080"+ context.getContextPath() + "/index.xhtml")
				.queryParam("msg", "Compra realizada com sucesso")
				.build();
				Response response = Response.seeOther(uri).build();
				ar.resume(response);
			}catch(Exception e) {
				ar.resume(new WebApplicationException(e));
			}
		});
	}
}