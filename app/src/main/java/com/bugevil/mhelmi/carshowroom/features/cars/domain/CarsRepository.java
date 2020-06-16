package com.bugevil.mhelmi.carshowroom.features.cars.domain;

import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import io.reactivex.Single;
import java.util.List;

public interface CarsRepository {
  Single<List<Car>> getCars(int page);
}
