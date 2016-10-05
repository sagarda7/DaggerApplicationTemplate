package np.com.sagardevkota.daggertest.dagger;

import np.com.sagardevkota.daggertest.MyApplication;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;
import np.com.sagardevkota.daggertest.dagger.components.DaggerAppComponent;
import np.com.sagardevkota.daggertest.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertest.dagger.modules.AppModule;
import np.com.sagardevkota.daggertest.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertest.dagger.modules.NetModule;
import np.com.sagardevkota.daggertest.dagger.modules.RealmModule;

/**
 * Created by Dell on 10/5/2016.
 */
public class Injector {

    private static AppComponent mAppComponent;

    private Injector() {}

    public static void initializeApplicationComponent(MyApplication application) {
        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(application))
                .netModule(new NetModule(application,"https://api.github.com"))
                .apiModule(new ApiModule())
                .databaseModule(new DatabaseModule(application))
                .realmModule(new RealmModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }


}
