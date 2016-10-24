package np.com.sagardevkota.daggertest.ui.main;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.tealbox.app.models.Repository;
import com.tealbox.app.models.login.LoginResponse;
import com.tealbox.app.networking.ApiInterface;
import com.tealbox.app.networking.ReactiveRequestHandler;
import com.tealbox.app.ui.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by Dell on 10/18/2016.
 */
public class MainPresenter extends BasePresenter<MainMvpView> {
    private String TAG = getClass().getName();
    @Inject
    ApiInterface apiInterface;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadData(Context context) {
        //get data from modal and present
        getMvpView().showName("Sagar");
    }

    public void fetchDataFromGithub(){
        Observable<ArrayList<Repository>> call = apiInterface.getRepository("sagarda7");
        ReactiveRequestHandler.performAsync(call, new ReactiveRequestHandler.RetroReactiveCallback<ArrayList<Repository>>() {
            @Override
            public void onComplete(ArrayList<Repository> response) {
                String data="";
                for(Repository repo: response){
                    data += repo.getName()+",";
                }
                getMvpView().showGithubNames(data);
            }

            @Override
            public void onError(HttpException ex) {

            }
        });
    }


    public void checkLogin(){
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
    }


}
