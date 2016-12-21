package np.com.sagardevkota.daggertemplate.ui.base;

import android.support.v4.app.Fragment;

import np.com.sagardevkota.daggertemplate.dagger.Injector;
import np.com.sagardevkota.daggertemplate.dagger.components.FragmentComponent;

/**
 * Created by Dell on 10/18/2016.
 */
public class BaseFragment extends Fragment {



    public FragmentComponent getFragmentComponent(){
        Injector.initialiseFragmentComponent(this);
        return Injector.getFragmentComponent();
    }



}
