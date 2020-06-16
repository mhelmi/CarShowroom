package com.bugevil.mhelmi.carshowroom.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class SchedulerModule {

  @Provides
  @Singleton
  @Named("BACKGROUND")
  Scheduler provideBackgroundScheduler() {
    return Schedulers.io();
  }

  @Provides
  @Singleton
  @Named("MAIN")
  Scheduler provideMainThreadScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
