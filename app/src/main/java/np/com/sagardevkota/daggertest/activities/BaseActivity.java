package np.com.sagardevkota.daggertest.activities;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import np.com.sagardevkota.daggertest.MyApplication;
import np.com.sagardevkota.daggertest.dagger.Injector;
import np.com.sagardevkota.daggertest.dagger.components.ActivityComponent;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.initializeActivityComponent(Injector.getMyApplication());
        setupComponent(Injector.getActivityComponent());
    }

    protected abstract void setupComponent(ActivityComponent component);

}
