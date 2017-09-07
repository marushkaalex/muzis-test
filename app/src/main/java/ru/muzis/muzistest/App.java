package ru.muzis.muzistest;

import android.app.Application;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.muzis.muzistest.api.ApiManager;

public class App extends Application {
    private static App sInstance;
    private Retrofit mRetrofit;
    private ApiManager mApiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mRetrofit = new Retrofit.Builder()
                .client(createHttpClient())
                .baseUrl(Consts.BASE_API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();
        mApiManager = mRetrofit.create(ApiManager.class);
    }

    public static App getInstance() {
        return sInstance;
    }

    public ApiManager getApiManager() {
        return mApiManager;
    }

    private OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", Consts.API_KEY)
                        .build();

                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        }).build();
    }
}
