package np.com.sagardevkota.daggertemplate.ui.main.presenters;

import android.content.Context;

import np.com.sagardevkota.daggertemplate.models.Repository;
import np.com.sagardevkota.daggertemplate.networking.ApiInterface;
import np.com.sagardevkota.daggertemplate.networking.ReactiveRequestHandler;
import np.com.sagardevkota.daggertemplate.ui.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import np.com.sagardevkota.daggertemplate.ui.main.views.MainMvpView;
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
        //get data from modal and present to view
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




}
