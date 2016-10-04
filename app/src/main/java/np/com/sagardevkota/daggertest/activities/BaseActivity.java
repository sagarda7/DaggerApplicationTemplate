package np.com.sagardevkota.daggertest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import np.com.sagardevkota.daggertest.MyApplication;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(((MyApplication)getApplication()).getAppComponent());
    }

    protected abstract void setupComponent(AppComponent component);

}
