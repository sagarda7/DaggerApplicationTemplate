package np.com.sagardevkota.daggertemplate.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import np.com.sagardevkota.daggertemplate.MyApplication;
import np.com.sagardevkota.daggertemplate.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.AppModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.NetModule;
import np.com.sagardevkota.daggertemplate.networking.ApiInterface;
import np.com.sagardevkota.daggertemplate.realm.RealmDatabase;

/**
 * Created by HP on 10/3/2016.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class, ApiModule.class})
public interface AppComponent {
    ApiInterface apiInterface(); //provided to subcomponents
    RealmDatabase database();
    void inject(MyApplication application);

}
