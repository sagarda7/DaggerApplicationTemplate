package np.com.sagardevkota.daggertest.dagger.components;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import np.com.sagardevkota.daggertest.MyApplication;
import np.com.sagardevkota.daggertest.activities.MainActivity;
import np.com.sagardevkota.daggertest.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertest.dagger.modules.AppModule;
import np.com.sagardevkota.daggertest.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertest.dagger.modules.NetModule;
import np.com.sagardevkota.daggertest.dagger.modules.RealmModule;
import np.com.sagardevkota.daggertest.dagger.scopes.ApplicationScope;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.realm.RealmRepository;

/**
 * Created by HP on 10/3/2016.
 */
@ApplicationScope
@Component(modules=AppModule.class)
public interface AppComponent {
    Application application();
    SharedPreferences sharedPreferences();
    RealmDatabase database();
    Context context();


}
