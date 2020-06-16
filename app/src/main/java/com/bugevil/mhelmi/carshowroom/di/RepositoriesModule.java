package com.bugevil.mhelmi.carshowroom.di;

import com.bugevil.mhelmi.carshowroom.features.cars.data.CarsRepositoryImpl;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.CarsRepository;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoriesModule {

  @Binds
  abstract CarsRepository bindCarsRepository(CarsRepositoryImpl CarsRepository);
}