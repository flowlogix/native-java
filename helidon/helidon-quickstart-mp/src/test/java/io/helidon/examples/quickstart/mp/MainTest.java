
package io.helidon.examples.quickstart.mp;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import io.helidon.metrics.api.MetricsFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@HelidonTest
class MainTest {

    @Inject
    private MetricRegistry registry;

    @Inject
    private WebTarget target;


    @BeforeAll
    static void clear() {
        MetricsFactory.closeAll();
    }


    @Test
    void testMicroprofileMetrics() {
        String message = target.path("simple-greet/Joe")
                .request()
                .get(String.class);

        assertThat(message, is("Hello Joe"));
        Counter counter = registry.counter("personalizedGets");
        double before = counter.getCount();

        message = target.path("simple-greet/Eric")
                .request()
                .get(String.class);

        assertThat(message, is("Hello Eric"));
        double after = counter.getCount();
        assertEquals(1d, after - before, "Difference in personalized greeting counter between successive calls");
    }

    @Test
    void testHealth() {
        Response response = target
                .path("health")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testGreet() {
        Message message = target
                .path("simple-greet")
                .request()
                .get(Message.class);
        assertThat(message.getMessage(), is("Hello World!"));
    }
                
    @Test
    void testGreetings() {
        Message jsonMessage = target
                .path("greet/Joe")
                .request()
                .get(Message.class);
        assertThat(jsonMessage.getMessage(), is("Hello Joe!"));

        try (Response r = target
                .path("greet/greeting")
                .request()
                .put(Entity.entity("{\"greeting\" : \"Hola\"}", MediaType.APPLICATION_JSON))) {
            assertThat(r.getStatus(), is(204));
        }

        jsonMessage = target
                .path("greet/Jose")
                .request()
                .get(Message.class);
        assertThat(jsonMessage.getMessage(), is("Hola Jose!"));
    }
                
}
