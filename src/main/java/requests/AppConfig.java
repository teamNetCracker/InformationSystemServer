package requests;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(TrackRequest.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(TrackRequest.class);
            }
        });
    }
}