package requests;

import com.google.inject.AbstractModule;
import db.DataBase;
import db.DataBaseInterface;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataBaseInterface.class).to(DataBase.class);
    }
}
