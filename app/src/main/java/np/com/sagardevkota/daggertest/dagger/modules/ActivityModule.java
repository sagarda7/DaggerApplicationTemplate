package np.com.sagardevkota.daggertest.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import np.com.sagardevkota.daggertest.dagger.scopes.ActivityScope;
import np.com.sagardevkota.daggertest.dagger.scopes.ApplicationScope;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;

/**
 * Created by HP on 10/3/2016.
 */
@Module
public class ActivityModule {

    Context mContext;

    public ActivityModule(Context context) {
        mContext = context;
    }



    @Provides
    @ActivityScope
    public Context activityContext() {
        return mContext;
    }


}