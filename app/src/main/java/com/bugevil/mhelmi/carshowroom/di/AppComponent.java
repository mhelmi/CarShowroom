package com.bugevil.mhelmi.carshowroom.di;

import android.content.Context;
import com.bugevil.mhelmi.carshowroom.features.cars.ui.HomeActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    ContextModule.class, WebServiceModule.class, SchedulerModule.class,
    ViewModelModule.class, RepositoriesModule.class
})
public interface AppComponent {
  Context getContext();

  // activities
  void inject(HomeActivity homeActivity);

  @Component.Factory
  interface Factory {
    AppComponent create(ContextModule contextModule);
  }
}
