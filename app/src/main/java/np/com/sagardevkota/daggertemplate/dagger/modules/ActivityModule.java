package np.com.sagardevkota.daggertemplate.dagger.modules;

import android.app.Activity;
import android.content.Context;

import np.com.sagardevkota.daggertemplate.dagger.scopes.PerActivity;
import np.com.sagardevkota.daggertemplate.ui.views.CustomProgressDialog;
import np.com.sagardevkota.daggertemplate.utils.formvalidator.FormValidator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HP on 10/22/2016.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    Context providesContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    FormValidator provideFormValidator(){
        return new FormValidator();
    }

    @Provides
    @PerActivity
    CustomProgressDialog providesCustomProgressDialog(Context c){
        return new CustomProgressDialog(c);
    }
}