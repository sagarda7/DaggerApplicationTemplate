package np.com.sagardevkota.daggertemplate.dagger.modules;

import dagger.Module;
import dagger.Provides;
import np.com.sagardevkota.daggertemplate.dagger.scopes.PerActivity;
import np.com.sagardevkota.daggertemplate.realm.RealmDatabase;

import np.com.sagardevkota.daggertemplate.realm.RealmRepository;

/**
 * Created by Dell on 10/4/2016.
 */

@Module
public class RealmModule {

    @Provides
    @PerActivity
    RealmRepository providesRealmRepository(RealmDatabase database) {
        return new RealmRepository(database);
    }
}
