package np.com.sagardevkota.daggertest.networking;

import java.util.ArrayList;

import np.com.sagardevkota.daggertest.models.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HP on 10/3/2016.
 */
public interface ApiInterface {
    @GET("/users/{user}/repos")
    Call<ArrayList<Repository>> getRepository(@Path("user") String userName);

}
