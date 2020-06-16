package com.bugevil.mhelmi.carshowroom.features.cars.domain;

import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class GetCarsUseCase {
  private CarsRepository carsRepository;

  @Inject
  public GetCarsUseCase(CarsRepository carsRepository) {
    this.carsRepository = carsRepository;
  }

  public Single<List<Car>> getCars(int page) {
    return carsRepository.getCars(page);
  }
}
