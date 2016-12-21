package np.com.sagardevkota.daggertemplate.realm;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dell on 10/5/2016.
 */
@Singleton
public class RealmDatabase {
    Context mContext;

    RealmConfiguration realmConfiguration;

    @Inject
    public RealmDatabase(Context context) {
        mContext=context;
    }

    public void setup() {
        /*if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration.Builder(mContext).build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } else {
            throw new IllegalStateException("database already configured");
        }*/

        Realm.init(mContext);
    }

    public Realm getRealmInstance() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(config);
    }


    public void close() {
        getRealmInstance().close();
    }
}
