package np.com.sagardevkota.daggertest.dagger.modules;

import dagger.Module;
import dagger.Provides;

import com.tealbox.app.dagger.scopes.PerActivity;
import com.tealbox.app.realm.RealmDatabase;
import com.tealbox.app.realm.RealmRepository;

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
