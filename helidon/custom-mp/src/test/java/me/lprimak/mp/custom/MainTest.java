
package me.lprimak.mp.custom;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import io.helidon.microprofile.testing.junit5.HelidonTest;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
class MainTest {

    @Inject
    private WebTarget target;

}
