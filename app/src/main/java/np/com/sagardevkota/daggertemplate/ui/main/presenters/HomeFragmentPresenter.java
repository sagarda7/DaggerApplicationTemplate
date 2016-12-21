package np.com.sagardevkota.daggertemplate.ui.main.presenters;

import android.util.Log;



import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import np.com.sagardevkota.daggertemplate.networking.ApiInterface;
import np.com.sagardevkota.daggertemplate.ui.base.BasePresenter;
import np.com.sagardevkota.daggertemplate.ui.main.views.HomeFragmentMvpView;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by Dell on 12/20/2016.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentMvpView> {
    private String TAG = getClass().getName();
    @Inject
    ApiInterface apiInterface;

    @Inject
    public HomeFragmentPresenter() {
    }

    @Override
    public void attachView(HomeFragmentMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }







    /*public void checkLogin(){
        Observable<LoginResponse> call = apiInterface.login("hari@lavaprotocols.com","3a5874478fb528fb567e3fdebc559436");
        ReactiveRequestHandler.performAsync(call, new ReactiveRequestHandler.RetroReactiveCallback<LoginResponse>() {
            @Override
            public void onComplete(LoginResponse response) {

                //getMvpView().showGithubNames(data);
                Log.d(TAG,response.getStatus().toString()+" is response");
            }

            @Override
            public void onError(HttpException ex) {
                Log.d(TAG,ex.message());
            }
        });
    }*/


    public void loadData(){
        getMvpView().showData("This is home and sagar");
    }
}