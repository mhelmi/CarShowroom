package com.bugevil.mhelmi.carshowroom.di;

import com.bugevil.mhelmi.carshowroom.BuildConfig;
import com.bugevil.mhelmi.carshowroom.features.cars.data.datasource.remote.CarsApiService;
import com.bugevil.mhelmi.carshowroom.di.annotitions.MainRetrofit;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServiceModule {

  @Singleton
  @Provides
  OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.level(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder()
      .connectTimeout(1, TimeUnit.MINUTES)
      .readTimeout(1, TimeUnit.MINUTES)
      .addInterceptor(logging)
      .build();
  }

  private Retrofit buildRetrofit(OkHttpClient okHttpClient, String baseUrl) {
    return new Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();
  }

  @Singleton @Provides @MainRetrofit
  Retrofit provideMainRetrofit(OkHttpClient okHttpClient) {
    return buildRetrofit(okHttpClient, BuildConfig.MAIN_URL);
  }

  @Provides
  @Singleton
  CarsApiService provideCarsApiService(@MainRetrofit Retrofit retrofit) {
    return retrofit.create(CarsApiService.class);
  }
}
