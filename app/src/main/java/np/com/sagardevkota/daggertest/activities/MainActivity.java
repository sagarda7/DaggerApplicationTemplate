package np.com.sagardevkota.daggertest.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import np.com.sagardevkota.daggertest.R;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;
import np.com.sagardevkota.daggertest.models.Repository;
import np.com.sagardevkota.daggertest.networking.ApiInterface;
import np.com.sagardevkota.daggertest.networking.ReactiveRequestHandler;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.realm.RealmRepository;
import np.com.sagardevkota.daggertest.sqllite.DBRepoHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Inject
    Retrofit retro;

    @Inject
    DBRepoHelper dbRepo;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    RealmRepository realmRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DEBUG",retro.baseUrl().toString());


        Repository repo=new Repository();
        repo.setName("sagar");
        repo.setFullName("sagar Devkota");
        repo.setDescription("description sagr");
        dbRepo.addItem(repo);


        List<Repository> repositories=dbRepo.getAllItems();
        for(Repository rep: repositories){
            Log.d("DEBUG",rep.getDescription()+" ");
        }

        realmRepository.add(repo);


        List<Repository> realmItems=realmRepository.all();
        for(Repository rep: realmItems){
            Log.d("DEBUG-REALM",rep.getDescription()+" ");
        }
    }

    @Override
    protected void setupComponent(AppComponent component) {
        component.inject(this);
    }

    public void onButtonClicked(View v){

        // Network call using retrofit and rxJava
        Observable<ArrayList<Repository>> call = mApiInterface.getRepository("codepath");

        ReactiveRequestHandler.performAsync(call, new ReactiveRequestHandler.RetroReactiveCallback<ArrayList<Repository>>() {
            @Override
            public void onComplete(ArrayList<Repository> response) {
                Log.d("DEBUG",response.size()+" items found");
            }

            @Override
            public void onError(HttpException ex) {
                Log.d("DEBUG",ex.code()+" "+ex.message());
            }
        });


    }


}
