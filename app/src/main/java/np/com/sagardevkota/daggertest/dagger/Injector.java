package np.com.sagardevkota.daggertest.dagger;

import android.app.Application;

import np.com.sagardevkota.daggertest.MyApplication;
import np.com.sagardevkota.daggertest.dagger.components.ActivityComponent;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;
import np.com.sagardevkota.daggertest.dagger.components.DaggerActivityComponent;
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
    private static ActivityComponent mActivityComponent;
    private static MyApplication myApplication;


    private Injector() {}

    public static void initializeApplicationComponent(MyApplication application) {
        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(application))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static void initializeActivityComponent(Application application) {
        mActivityComponent = DaggerActivityComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appComponent(mAppComponent)
                .netModule(new NetModule(application, "https://api.github.com"))
                .apiModule(new ApiModule())
                .databaseModule(new DatabaseModule())
                .realmModule(new RealmModule())
                .build();
    }

    public static ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public static Application getMyApplication() {
        return myApplication;
    }


}
