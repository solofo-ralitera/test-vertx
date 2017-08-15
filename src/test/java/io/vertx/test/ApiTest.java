package io.vertx.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;

@RunWith(VertxUnitRunner.class)
public class ApiTest {

    private Vertx vertx;

    @Before
    public void before(final TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(Api.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void after(final TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApi(final TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient() //
                .getNow(3001, "localhost", "/hello", response -> {
                    response.handler(body -> {
                        //context.assertTrue(body.toString().contains(EXPECTED_MESSAGE_HELLO));
                        async.complete();
                    });
                });
    }
}
