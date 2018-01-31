package br.com.casadocodigo.loja.websockets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.casadocodigo.loja.models.Promo;

@ServerEndpoint(value="/canal/promos")
public class PromosEndPoint {
	
	@Inject 
	private UsuariosSession usuarios;
	
	@OnOpen
	public void onMessage(Session session) {
		usuarios.add(session);
	}
	
	public void send(Promo promo)  {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Session> sessions = usuarios.getUsuarios();
		for (Session session : sessions) {
			if(session.isOpen()) {
				try {
					session.getBasicRemote().sendText("{\"titulo\": \"Livro Java OO com 40% de desconto\", \"livroId\": 19}");
				}catch(IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		usuarios.remove(session);
		System.out.println(closeReason.getCloseCode());
	}

}
