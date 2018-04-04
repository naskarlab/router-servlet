package com.naskar.jsonservlet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Assert;
import org.junit.Test;

import com.naskar.jsonservlet.model.Resposta;
import com.naskar.jsonservlet.model.Solicitacao;
import com.naskar.router.servlet.Router;
import com.naskar.router.servlet.impl.In;
import com.naskar.router.servlet.impl.Out;
import com.naskar.router.servlet.impl.RouterServlet;

public class RouterServletTest {

	@Test
	public void testSuccess() throws Exception {

		Server server = create((router) -> { 
    		router.from("/api/service")
    			.to(In.fromJson(Solicitacao.class), Out.toJson())
    			.execute((s) -> {
    				
    				Resposta r = new Resposta();
    				r.setMsg(s.getName() + ", Hello!");
    				return r;
    				
    			});

        });
		
		String json = "{ \"name\": \"rafael\"}";
		String expected = "{\"msg\":\"rafael, Hello!\"}";
		
		String result = post(server.getURI().toString() + "api/service", json);
		
		server.stop();
		
		Assert.assertTrue(result.equals(expected));
	}
	
	@Test
	public void testSuccessReqRes() throws Exception {

		Server server = create((router) -> { 
    		router.from("/api/service")
    			.to(In.fromJson(Solicitacao.class), Out.toJson())
    			.execute((req, res, s) -> {
    				
    				Resposta r = new Resposta();
    				r.setMsg(s.getName() + ", Hello: " + req.getHeader("key"));
    				return r;
    				
    			});

        });
		
		String json = "{ \"name\": \"rafael\"}";
		String expected = "{\"msg\":\"rafael, Hello: 1234\"}";
		
		String result = post(server.getURI().toString() + "api/service", json);
		
		server.stop();
		
		Assert.assertTrue(result.equals(expected));
	}
	
	@Test
	public void testFail() throws Exception {

		Server server = create((router) -> { 
    		router.from("/api/service")
    			.to(In.fromJson(Solicitacao.class), Out.toJson())
    			.execute((req, res, s) -> {
    				
    				Resposta r = new Resposta();
    				if(s != null) {
    					r.setMsg(s.getName() + ", Hello: " + req.getHeader("key"));
    				} else {
    					r.setMsg("null");
    				}
    				return r;
    				
    			});

        });
		
		String json = null;
		String expected = "{\"msg\":\"null\"}";
		
		String result = post(server.getURI().toString() + "api/service", json);
		
		server.stop();
		
		Assert.assertTrue(result.equals(expected));
	}
	
	private Server create(Consumer<Router> call) throws Exception {
		
		Server server = new Server(8080);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new RouterServlet(call)),"/*");
		server.start();
		
		return server;
	}

	private static String post(String url, String body) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("key", "1234");

		con.setDoOutput(true);
		if(body != null) {
			con.getOutputStream().write(body.getBytes());
		}
		con.getOutputStream().close();

		return read(con.getInputStream());
	}

	private static String read(InputStream in) throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	}

}
