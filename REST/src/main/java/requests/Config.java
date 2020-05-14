package requests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("")
public class Config extends ResourceConfig {
    public Config() {
        packages("requests");
        Injector injector = Guice.createInjector(new GuiceModule());
        HK2toGuiceModule hk2Module = new HK2toGuiceModule(injector);
        register(hk2Module);
    }
}
