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
import np.com.sagardevkota.daggertest.dagger.scopes.ActivityScope;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.realm.RealmRepository;

/**
 * Created by HP on 10/3/2016.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules={NetModule.class, ApiModule.class, DatabaseModule.class, RealmModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);

    // Realm repositories tables
    void inject(RealmRepository realmRepository);

    void inject(DatabaseModule databaseModule);

}
