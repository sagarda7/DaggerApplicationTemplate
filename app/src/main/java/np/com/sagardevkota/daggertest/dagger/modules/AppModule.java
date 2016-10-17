package np.com.sagardevkota.daggertest.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import np.com.sagardevkota.daggertest.dagger.scopes.ApplicationScope;
import np.com.sagardevkota.daggertest.dagger.scopes.UserScope;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.sqllite.DBRepoHelper;

/**
 * Created by HP on 10/3/2016.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    Application providesApplication() {
        return mApplication;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @ApplicationScope
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @ApplicationScope
    public Context applicationContext() {
        return mApplication.getApplicationContext();
    }

    //database instance exists throughout application scope so here
    @Provides
    @ApplicationScope
    RealmDatabase providesRealmDatabase() {
        return new RealmDatabase();
    }

}