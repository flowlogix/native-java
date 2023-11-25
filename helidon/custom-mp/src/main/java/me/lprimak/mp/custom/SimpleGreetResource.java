
package me.lprimak.mp.custom;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/simple-greet
 *
 * The message is returned as a JSON object.
 */
@Path("/simple-greet")
public class SimpleGreetResource {

    private final String message;

    @Inject
    public SimpleGreetResource(@ConfigProperty(name = "app.greeting") String message) {
        this.message = message;
    }

    /**
     * Return a worldly greeting message.
     *
     * @return {@link JsonObject}
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDefaultMessage() {
        return String.format("%s %s!", message, "World");
    }


}
