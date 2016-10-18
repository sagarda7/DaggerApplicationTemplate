package np.com.sagardevkota.daggertest.realm;

import android.content.Context;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import np.com.sagardevkota.daggertest.dagger.Injector;

/**
 * Created by Dell on 10/5/2016.
 */
public class RealmDatabase {

    RealmConfiguration realmConfiguration;
    Context mContext;
    public RealmDatabase(Context context) {
        this.mContext=context;
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
        return Realm.getDefaultInstance();
    }


    public void close() {
        getRealmInstance().close();
    }
}
