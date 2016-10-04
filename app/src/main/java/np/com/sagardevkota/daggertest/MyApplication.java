package np.com.sagardevkota.daggertest;

import android.app.Application;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;
import np.com.sagardevkota.daggertest.dagger.components.DaggerAppComponent;
import np.com.sagardevkota.daggertest.dagger.modules.ApiModule;
import np.com.sagardevkota.daggertest.dagger.modules.AppModule;
import np.com.sagardevkota.daggertest.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertest.dagger.modules.NetModule;


/**
 * Created by HP on 10/3/2016.
 */
public class MyApplication extends Application {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))
                .netModule(new NetModule(this,"https://api.github.com"))
                .apiModule(new ApiModule())
                .databaseModule(new DatabaseModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}