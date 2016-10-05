package np.com.sagardevkota.daggertest;

import android.app.Application;

import javax.inject.Inject;

import np.com.sagardevkota.daggertest.dagger.Injector;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;
import np.com.sagardevkota.daggertest.dagger.components.DaggerAppComponent;
import np.com.sagardevkota.daggertest.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertest.dagger.modules.AppModule;
import np.com.sagardevkota.daggertest.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertest.dagger.modules.NetModule;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;


/**
 * Created by HP on 10/3/2016.
 */
public class MyApplication extends Application {

    @Inject
    RealmDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initializeApplicationComponent(this);
        Injector.getAppComponent().inject(this);
        database.setup();
    }


}