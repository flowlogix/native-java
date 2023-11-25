
module me.lprimak.mp.custom {
    requires jakarta.json;
    requires jersey.media.multipart;
    requires org.jvnet.mimepull;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires jakarta.ws.rs;
    requires io.helidon;
    requires io.helidon.microprofile.config;
    requires io.helidon.microprofile.server;

    exports me.lprimak.mp.custom;

    opens me.lprimak.mp.custom;
}