package np.com.sagardevkota.daggertemplate.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import np.com.sagardevkota.daggertemplate.dagger.scopes.PerActivity;

import np.com.sagardevkota.daggertemplate.sqllite.DBRepoHelper;

/**
 * Created by Dell on 10/4/2016.
 */

@Module
public class DatabaseModule {
    public DatabaseModule(){
    }

    @Provides
    @PerActivity
    DBRepoHelper providesRepoDB(Context c) {
        return new DBRepoHelper(c);
    }
}
