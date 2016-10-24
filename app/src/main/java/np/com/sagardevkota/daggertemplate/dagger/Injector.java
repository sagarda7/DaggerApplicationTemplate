package np.com.sagardevkota.daggertemplate.dagger;


import np.com.sagardevkota.daggertemplate.MyApplication;
import np.com.sagardevkota.daggertemplate.common.Const;
import np.com.sagardevkota.daggertemplate.dagger.components.ActivityComponent;
import np.com.sagardevkota.daggertemplate.dagger.components.AppComponent;
import np.com.sagardevkota.daggertemplate.dagger.components.DaggerActivityComponent;
import np.com.sagardevkota.daggertemplate.dagger.components.DaggerAppComponent;
import np.com.sagardevkota.daggertemplate.dagger.modules.ActivityModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.AppModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.NetModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.RealmModule;
import np.com.sagardevkota.daggertemplate.ui.base.BaseActivity;

/**
 * Created by Dell on 10/5/2016.
 */
public class Injector {

    private static AppComponent mAppComponent;
    private static ActivityComponent mActivityComponent;

    private Injector() {}

    public static void initializeApplicationComponent(MyApplication application) {
        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(application))
                .netModule(new NetModule(application, Const.API_URL))
                .apiModule(new ApiModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static void initialiseActivityComponent(BaseActivity activity) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                // list of modules that are part of this component need to be created here too
                .activityModule(new ActivityModule(activity))
                .databaseModule(new DatabaseModule())
                .realmModule(new RealmModule())
                .build();
    }

    public static ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


}
