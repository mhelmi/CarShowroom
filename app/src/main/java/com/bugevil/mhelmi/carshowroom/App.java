package com.bugevil.mhelmi.carshowroom;

import android.app.Application;
import com.bugevil.mhelmi.carshowroom.di.AppComponent;
import com.bugevil.mhelmi.carshowroom.di.ContextModule;
import com.bugevil.mhelmi.carshowroom.di.DaggerAppComponent;

public class App extends Application {
  private static App INSTANCE = null;
  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    INSTANCE = this;
    appComponent = DaggerAppComponent.factory()
      .create(new ContextModule(this));
  }

  public static App getInstance() {
    return INSTANCE;
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
