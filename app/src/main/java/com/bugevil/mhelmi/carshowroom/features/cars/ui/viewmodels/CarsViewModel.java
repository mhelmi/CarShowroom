package com.bugevil.mhelmi.carshowroom.features.cars.ui.viewmodels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.GetCarsUseCase;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import com.bugevil.mhelmi.carshowroom.utils.result.Result;
import com.bugevil.mhelmi.carshowroom.utils.viewmodel.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class CarsViewModel extends BaseViewModel {
  private static final String TAG = CarsViewModel.class.getSimpleName();
  private GetCarsUseCase getCarsUseCase;

  private MutableLiveData<Result<List<Car>>> carsResult = new MutableLiveData<>();

  @Inject
  public CarsViewModel(GetCarsUseCase getCarsUseCase) {
    this.getCarsUseCase = getCarsUseCase;
  }

  public LiveData<Result<List<Car>>> getCarsResult() {
    return carsResult;
  }

  public void loadCars(int page) {
    add(getCarsUseCase.getCars(page)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe(d -> carsResult.postValue(Result.loading()))
      .subscribe(cars -> carsResult.postValue(Result.success(cars)), t -> {
        Log.e(TAG, "loadCars: ", t);
        carsResult.postValue(Result.connectionError());
      })
    );
  }
}
