package np.com.sagardevkota.daggertest.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tealbox.app.dagger.Injector;
import com.tealbox.app.dagger.components.ActivityComponent;
import com.tealbox.app.dagger.components.AppComponent;

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
