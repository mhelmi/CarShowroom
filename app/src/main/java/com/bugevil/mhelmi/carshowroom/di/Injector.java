package com.bugevil.mhelmi.carshowroom.di;

import com.bugevil.mhelmi.carshowroom.App;

public class Injector {
  public static AppComponent get() {
    return App.getInstance().getAppComponent();
  }
}
