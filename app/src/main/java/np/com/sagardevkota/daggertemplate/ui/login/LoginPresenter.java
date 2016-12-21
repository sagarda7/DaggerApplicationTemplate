package np.com.sagardevkota.daggertemplate.ui.login;

import javax.inject.Inject;

import np.com.sagardevkota.daggertemplate.networking.ApiInterface;
import np.com.sagardevkota.daggertemplate.ui.base.BasePresenter;

/**
 * Created by Dell on 10/18/2016.
 */
public class LoginPresenter extends BasePresenter<LoginMvpView> {
    private String TAG = getClass().getName();

    @Inject
    ApiInterface apiInterface;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void checkLogin(String username, String password){
       // make network call or get dtat from model with then if success fire view callback
        getMvpView().onLoginSuccess();
       /* if(username.equals("sagar@yahoo.com") && password.equals("test")){
            getMvpView().onLoginSuccess();
        }
        else {
            getMvpView().onLoginError();
        }*/

    }


}
