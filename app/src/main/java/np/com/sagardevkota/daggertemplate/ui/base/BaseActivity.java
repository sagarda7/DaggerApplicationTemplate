package np.com.sagardevkota.daggertemplate.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import np.com.sagardevkota.daggertemplate.dagger.Injector;
import np.com.sagardevkota.daggertemplate.dagger.components.ActivityComponent;

/**
 * Created by Dell on 10/18/2016.
 */
public class BaseActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.initialiseActivityComponent(this);
    }


    public ActivityComponent getActivityComponent(){
        return Injector.getActivityComponent();
    }

}
