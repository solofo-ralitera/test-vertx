package io.vertx.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

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
    public void start(Future<Void> fut) {
        vertx
                .createHttpServer()
                .requestHandler(r -> {
                    r.response().end("Hello world");
                })
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }

}
