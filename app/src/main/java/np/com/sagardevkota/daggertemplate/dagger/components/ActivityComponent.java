package np.com.sagardevkota.daggertemplate.dagger.components;

import np.com.sagardevkota.daggertemplate.dagger.modules.ActivityModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.RealmModule;
import np.com.sagardevkota.daggertemplate.dagger.scopes.PerActivity;
import np.com.sagardevkota.daggertemplate.ui.login.LoginActivity;
import np.com.sagardevkota.daggertemplate.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by HP on 10/22/2016.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, RealmModule.class,  DatabaseModule.class})
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivity loginActivity);

}

