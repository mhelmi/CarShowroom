package com.bugevil.mhelmi.carshowroom.features.cars.data.datasource.remote;

import com.bugevil.mhelmi.carshowroom.features.cars.data.response.GetCarsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarsApiService {
  @GET("api/v1/cars")
  Single<GetCarsResponse> getCars(@Query("page") int page);
}
