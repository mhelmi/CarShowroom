package com.bugevil.mhelmi.carshowroom.di;

import androidx.lifecycle.ViewModel;
import com.bugevil.mhelmi.carshowroom.di.annotitions.ViewModelKey;
import com.bugevil.mhelmi.carshowroom.features.cars.ui.viewmodels.CarsViewModel;
import com.bugevil.mhelmi.carshowroom.utils.viewmodel.ViewModelFactory;
import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import javax.inject.Provider;

@Module
public abstract class ViewModelModule {
  @Binds @IntoMap @ViewModelKey(CarsViewModel.class)
  abstract ViewModel provideCarsViewModel(CarsViewModel carsViewModel);
}
