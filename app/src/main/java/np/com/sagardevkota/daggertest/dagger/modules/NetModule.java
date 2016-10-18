package np.com.sagardevkota.daggertest.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import np.com.sagardevkota.daggertest.dagger.scopes.ActivityScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 10/3/2016.
 */
@Module
public class NetModule {

    String mBaseUrl;
    Application mApplication;


    // Constructor needs one parameter to instantiate.
    public NetModule(Application application,String baseUrl) {

        this.mBaseUrl = baseUrl;
        this.mApplication=application;
    }


    @Provides
    @ActivityScope
    Cache provideOkHttpCache() {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(mApplication.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @ActivityScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @ActivityScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient
                .Builder().cache(cache)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return client;
    }

    @Provides
    @ActivityScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
