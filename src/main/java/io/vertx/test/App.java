package io.vertx.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Override
    public void start(Future<Void> startFuture) {
        final Router router = Router.router(vertx);

        router.get("/").handler((RoutingContext req) -> req.response().end(""));

        router.get("/hello/")
            .handler((RoutingContext req) -> req.response()
                .putHeader("content-type", "text/html")
                .end("<html><body><h1>Hello World</h1></body></html>")
            );

        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(8080, (AsyncResult<HttpServer> result) -> {
                if (result.succeeded()) {
                    startFuture.complete();
                } else {
                    startFuture.fail(result.cause());
                }
            });
    }

}
