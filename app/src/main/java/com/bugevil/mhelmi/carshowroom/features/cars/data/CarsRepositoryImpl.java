package com.bugevil.mhelmi.carshowroom.features.cars.data;

import com.bugevil.mhelmi.carshowroom.features.cars.data.datasource.remote.CarsApiService;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.CarsRepository;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import com.bugevil.mhelmi.carshowroom.utils.Const;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class CarsRepositoryImpl implements CarsRepository {
  private CarsApiService carsApiService;

  @Inject CarsRepositoryImpl(CarsApiService carsApiService) {
    this.carsApiService = carsApiService;
  }

  @Override public Single<List<Car>> getCars(int page) {
    return carsApiService.getCars(page)
      .map(getCarsResponse -> {
        if (getCarsResponse.getStatus() == Const.SUCCESS) {
          return getCarsResponse.getCars();
        }
        return null;
      });
  }
}
