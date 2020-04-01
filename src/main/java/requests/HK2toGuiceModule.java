package requests;

import com.google.inject.Injector;
import model.DataBaseInterface;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class HK2toGuiceModule extends AbstractBinder {
    private Injector guiceInjector;

    public HK2toGuiceModule(Injector guiceInjector) {
        this.guiceInjector = guiceInjector;
    }

    @Override
    protected void configure() {
        bindFactory(new ServiceFactory<DataBaseInterface>(guiceInjector, DataBaseInterface.class)).to(DataBaseInterface.class);
    }

    private static class ServiceFactory<T> implements Factory<T> {

        private final Injector guiceInjector;

        private final Class<T> DataBaseInterface;

        public ServiceFactory(Injector guiceInjector, Class<T> serviceClass) {
            this.guiceInjector = guiceInjector;
            this.DataBaseInterface = serviceClass;
        }

        @Override
        public T provide() {
            return guiceInjector.getInstance(DataBaseInterface);
        }

        @Override
        public void dispose(T versionResource) {
        }
    }
}
