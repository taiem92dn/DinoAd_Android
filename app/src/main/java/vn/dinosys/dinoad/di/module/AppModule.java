package vn.dinosys.dinoad.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by htsi.
 * Since: 7/1/16 on 6:02 PM
 * Project: DinoAd
 */
@Module
public class AppModule {

    private final Application mApplication;


    public AppModule(Application pApplication) {
        mApplication = pApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() { return mApplication;}

    @Provides
    @Singleton
    @Named("REST_ADAPTER")
    Retrofit provideRestAdapter() {
        Log.d("TAG", "create rest adapter");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.34:8080");
        return builder.build();
    }
}
