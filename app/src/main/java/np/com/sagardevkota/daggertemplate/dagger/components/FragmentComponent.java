package np.com.sagardevkota.daggertemplate.dagger.components;



import dagger.Component;
import np.com.sagardevkota.daggertemplate.dagger.modules.DatabaseModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.FragmentModule;
import np.com.sagardevkota.daggertemplate.dagger.modules.RealmModule;
import np.com.sagardevkota.daggertemplate.dagger.scopes.PerFragment;
import np.com.sagardevkota.daggertemplate.ui.main.fragments.HomeFragment;

/**
 * Created by HP on 10/22/2016.
 */

@PerFragment
@Component(dependencies = AppComponent.class, modules = {FragmentModule.class, RealmModule.class,  DatabaseModule.class})
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);


}

