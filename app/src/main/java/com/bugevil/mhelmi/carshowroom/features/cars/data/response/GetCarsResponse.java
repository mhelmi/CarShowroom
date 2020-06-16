package com.bugevil.mhelmi.carshowroom.features.cars.data.response;

import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetCarsResponse {

  @SerializedName("status")
  private int status;
  @SerializedName("data")
  private List<Car> cars;

  public int getStatus() {
    return status;
  }

  public List<Car> getCars() {
    return cars;
  }
}
