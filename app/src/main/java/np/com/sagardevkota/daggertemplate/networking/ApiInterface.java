package np.com.sagardevkota.daggertemplate.networking;

import java.util.ArrayList;

import np.com.sagardevkota.daggertemplate.models.Repository;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by HP on 10/3/2016.
 */
public interface ApiInterface {
    @GET("/users/{user}/repos")
    Observable<ArrayList<Repository>> getRepository(@Path("user") String userName);


    /*@POST("/api/v1/login")
    Observable<LoginResponse> login(@Header("username") String email, @Header("password") String password);*/




}
