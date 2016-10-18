package np.com.sagardevkota.daggertest.dagger.modules;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import np.com.sagardevkota.daggertest.dagger.scopes.ActivityScope;
import np.com.sagardevkota.daggertest.sqllite.DBRepoHelper;

/**
 * Created by Dell on 10/4/2016.
 */

@Module
public class DatabaseModule {
    @Inject
    Context context;


    @Provides
    @ActivityScope
    DBRepoHelper providesRepoDB() {
        return new DBRepoHelper(context);
    }
}
