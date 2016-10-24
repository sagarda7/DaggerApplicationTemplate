package np.com.sagardevkota.daggertemplate.realm;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import np.com.sagardevkota.daggertemplate.models.Repository;

/**
 * Created by Dell on 10/5/2016.
 */
public class RealmRepository {

    RealmDatabase database;
    Realm realm;
    public RealmRepository(RealmDatabase database){
        this.database=database;
        this.realm = database.getRealmInstance();
    }

    public void add(Repository repository){
        Log.d("DEBUG","add called in realm");
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Repository repo = realm.createObject(Repository.class);
                repo.setName("Hari");
                repo.setFullName("Hari Bastola");
                repo.setDescription("Nepal");

            }
        });
    }


    public List<Repository> all(){
        return realm.where(Repository.class).findAll();
    }
}
