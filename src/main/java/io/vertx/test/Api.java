package io.vertx.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;


public class Api extends AbstractVerticle
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Override
    public void start(Future<Void> startFuture) {
        final Router router = Router.router(vertx);

        router.get("/api").handler((RoutingContext req) -> req.response().end(""));

        router.get("/api/hello/")
                .handler((RoutingContext req) -> req.response()
                        .putHeader("content-type", "application/json")
                        .end("{ \"message\" : \"Hello World!\" }")
                );

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(3001, (AsyncResult<HttpServer> result) -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }

}
