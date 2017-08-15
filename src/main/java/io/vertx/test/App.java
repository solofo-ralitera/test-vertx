package io.vertx.test;

import io.vertx.core.AbstractVerticle;

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
    public void start() throws Exception {

        vertx.deployVerticle(Api.class.getCanonicalName());
        vertx.deployVerticle(Mongo.class.getCanonicalName());

    }

}
