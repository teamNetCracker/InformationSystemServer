package requests;

import com.google.inject.AbstractModule;
import model.DataBase;
import model.DataBaseInterface;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataBaseInterface.class).to(DataBase.class);
    }
}
