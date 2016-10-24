package np.com.sagardevkota.daggertest.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

import com.tealbox.app.dagger.scopes.PerActivity;
import com.tealbox.app.sqllite.DBRepoHelper;

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
