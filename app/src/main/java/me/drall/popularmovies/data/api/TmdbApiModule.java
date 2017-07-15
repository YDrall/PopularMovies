package me.drall.popularmovies.data.api;


import com.google.gson.GsonBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.drall.popularmovies.AppScope;
import me.drall.popularmovies.BuildConfig;
import me.drall.popularmovies.data.api.configuration.ApiKeyInterceptor;
import me.drall.popularmovies.data.api.configuration.AutoValueGsonFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TmdbApiModule {

    String baseUrl;

    public TmdbApiModule(String url) {
        this.baseUrl =url;
    }

    @Provides
    @AppScope
    ApiKeyInterceptor provideApiKeyInterceptor() {
        return new ApiKeyInterceptor(BuildConfig.TMDB_API_TOKEN);
    }

    @Provides
    @AppScope
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @AppScope
    OkHttpClient providesOkHttpClient(ApiKeyInterceptor apiKeyInterceptor, HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(loggingInterceptor);
        httpClientBuilder.addInterceptor(apiKeyInterceptor);
        return httpClientBuilder.build();
    }

    @Provides
    @AppScope
    GsonConverterFactory provideGsonConvertorFactory() {
        return GsonConverterFactory.create(new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                .create());
    }

    @Provides
    @AppScope
    @Named("callAdapterFactory")
    CallAdapter.Factory providesRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(OkHttpClient httpClient, GsonConverterFactory gsonConverterFactory, @Named("callAdapterFactory")CallAdapter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(factory)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient)
                .build();
    }

    @Provides
    @AppScope
    TmdbService provideTmdbService(Retrofit retrofit) {
        return retrofit.create(TmdbService.class);
    }
}
