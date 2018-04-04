# Router Servletrouter-servlet:

Create easily web services using JSON and a simple servlet.  

## Features

* Small and fast.
* The GSON is only dependency.
* Use a simple servlet.


## Examples

```

// Jetty Server
Server server = new Server(8082);

ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
context.setContextPath("/context");
server.setHandler(context);

// RouterServlet
context.addServlet(new ServletHolder(new RouterServlet() {
	
	private static final long serialVersionUID = 1L;

	// Override the configure() method to define routes
	@Override
	protected void configure(ServletConfig config, Router router) {
		router.from("/api/service")
				.to(In.fromJson(Solicitacao.class), Out.toJson())
				.execute((req, res, s) -> {
					
					Resposta r = new Resposta();
					r.setMsg(s.getName() + ", Hello: " + req.getHeader("key"));
					return r;
					
				});
	}
	
}),"/servlet/*");
server.start();
```



## Usage with Maven

```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
    <groupId>com.github.naskarlab</groupId>
    <artifactId>router-servlet</artifactId>
    <version>0.0.1</version>
</dependency>

```


## Releases

### 0.0.1 
	- First release.

