package np.com.sagardevkota.daggertemplate;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import np.com.sagardevkota.daggertemplate.dagger.Injector;
import np.com.sagardevkota.daggertemplate.realm.RealmDatabase;

import javax.inject.Inject;


/**
 * Created by HP on 10/3/2016.
 */
public class MyApplication extends Application {

    @Inject
    RealmDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initializeApplicationComponent(this);
        Injector.getAppComponent().inject(this);
        database.setup();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());
    }


}