package np.com.sagardevkota.daggertest.activities;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import np.com.sagardevkota.daggertest.R;
import np.com.sagardevkota.daggertest.dagger.components.AppComponent;
import np.com.sagardevkota.daggertest.models.Repository;
import np.com.sagardevkota.daggertest.networking.ApiInterface;
import np.com.sagardevkota.daggertest.realm.RealmDatabase;
import np.com.sagardevkota.daggertest.realm.RealmRepository;
import np.com.sagardevkota.daggertest.sqllite.DBRepoHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        Call<ArrayList<Repository>> call = mApiInterface.getRepository("codepath");

        call.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                if (response.isSuccessful()) {
                    Log.i("DEBUG", response.body().toString());

                } else {
                    Log.i("ERROR", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {

            }


        });

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


}
