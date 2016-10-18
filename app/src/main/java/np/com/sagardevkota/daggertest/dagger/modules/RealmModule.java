package np.com.sagardevkota.daggertest.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import np.com.sagardevkota.daggertest.dagger.scopes.ActivityScope;
import np.com.sagardevkota.daggertest.dagger.scopes.ApplicationScope;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.realm.RealmRepository;
import np.com.sagardevkota.daggertest.sqllite.DBRepoHelper;

/**
 * Created by Dell on 10/4/2016.
 */

@Module
public class RealmModule {

    @Provides
    @ActivityScope
    RealmRepository providesRealmRepository() {
        return new RealmRepository();
    }
}
