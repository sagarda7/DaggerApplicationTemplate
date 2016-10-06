package np.com.sagardevkota.daggertest.networking;

import android.util.Log;

import java.util.ArrayList;

import np.com.sagardevkota.daggertest.models.Repository;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Dell on 10/6/2016.
 */
public class ReactiveRequestHandler {

    public static <T> void performAsync(final Observable<T> call, final RetroReactiveCallback<T> callback){
        Action1<T> response= new Action1<T>() {
            @Override
            public void call(T t) {
                callback.onComplete(t);
            }
        };

        Action1<Throwable> error= new Action1<Throwable>() {
            @Override
            public void call(Throwable t) {
                if(t instanceof HttpException){
                    callback.onError((HttpException) t);
                }
            }
        };

        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response,error);

    }



    public interface RetroReactiveCallback<T>{
        public void onComplete(T response);
        public void onError(HttpException ex);
    }


}