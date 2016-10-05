package np.com.sagardevkota.daggertest.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import np.com.sagardevkota.daggertest.MyApplication;
import np.com.sagardevkota.daggertest.activities.MainActivity;
import np.com.sagardevkota.daggertest.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertest.dagger.modules.AppModule;
import np.com.sagardevkota.daggertest.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertest.dagger.modules.NetModule;
import np.com.sagardevkota.daggertest.dagger.modules.RealmModule;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.realm.RealmRepository;

/**
 * Created by HP on 10/3/2016.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class, ApiModule.class, DatabaseModule.class, RealmModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MyApplication application);

    void inject(RealmDatabase database);
    void inject(RealmRepository realmRepository);

}
