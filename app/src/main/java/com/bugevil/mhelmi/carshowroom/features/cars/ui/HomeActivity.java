package com.bugevil.mhelmi.carshowroom.features.cars.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import com.bugevil.mhelmi.carshowroom.databinding.ActivityHomeBinding;
import com.bugevil.mhelmi.carshowroom.databinding.ContentMainBinding;
import com.bugevil.mhelmi.carshowroom.di.Injector;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import com.bugevil.mhelmi.carshowroom.features.cars.ui.adapters.CarsAdapter;
import com.bugevil.mhelmi.carshowroom.features.cars.ui.viewmodels.CarsViewModel;
import com.bugevil.mhelmi.carshowroom.utils.EndlessRecyclerViewScrollListener;
import com.bugevil.mhelmi.carshowroom.utils.ErrorUtils;
import com.bugevil.mhelmi.carshowroom.utils.result.Result;
import com.bugevil.mhelmi.carshowroom.utils.viewmodel.ViewModelFactory;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements CarsAdapter.OnCarClickListener,
  SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = HomeActivity.class.getSimpleName();
  public static final int START_PAGE = 1;
  public static final int TOTAL_PAGES = 4;
  private CompositeDisposable disposables = new CompositeDisposable();
  private ActivityHomeBinding activityHomeBinding;
  private ContentMainBinding binding;
  private Context context;
  private FragmentActivity activity;
  private CarsAdapter carsAdapter;
  private CarsViewModel carsViewModel;
  @Inject ViewModelFactory viewModelFactory;
  private EndlessRecyclerViewScrollListener endlessScrollListener;
  private int currentPage = START_PAGE;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Injector.get().inject(this);
    activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
    setContentView(activityHomeBinding.getRoot());
    setSupportActionBar(activityHomeBinding.toolbar);
    binding = activityHomeBinding.contentMain;
    context = this;
    activity = this;
    setupCarsRecyclerView();
    addRefreshListener();
    initViewModels();
    setObservers();
    loadCars(currentPage);
  }

  private void setupCarsRecyclerView() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    binding.rvCars.setLayoutManager(layoutManager);
    carsAdapter = new CarsAdapter(this);
    binding.rvCars.setAdapter(carsAdapter);
    addEndScrollListener(layoutManager);
  }

  private void addEndScrollListener(LinearLayoutManager layoutManager) {
    endlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        if (currentPage < TOTAL_PAGES) {
          loadCars(++currentPage);
        }
      }
    };
    binding.rvCars.addOnScrollListener(endlessScrollListener);
  }

  private void addRefreshListener() {
    binding.swipeRefreshLayout.setOnRefreshListener(this);
  }

  private void initViewModels() {
    carsViewModel = new ViewModelProvider(activity, viewModelFactory).get(CarsViewModel.class);
  }

  private void setObservers() {
    carsViewModel.getCarsResult().observe(this, this::handleCarsResult);
  }

  private void loadCars(int page) {
    carsViewModel.loadCars(page);
  }

  private void handleCarsResult(Result<List<Car>> result) {
    switch (result.getResultType()) {
      case LOADING:
        showLoading(true);
        hideError();
        break;
      case SUCCESS:
        showLoading(false);
        hideError();
        submitCarList(result.getData());
        break;
      case CONNECTION_ERROR:
        showLoading(false);
        showError();
        break;
    }
  }

  private void showLoading(boolean isLoading) {
    if (currentPage == START_PAGE) {
      binding.swipeRefreshLayout.setRefreshing(isLoading);
    } else {
      binding.progressBarLoadMore.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
  }

  private void submitCarList(List<Car> cars) {
    if (currentPage == START_PAGE) {
      carsAdapter.setCarList(cars);
    } else {
      carsAdapter.addAllCarList(cars);
    }
  }

  private void showError() {
    ErrorUtils.showError(binding.layoutError, disposables,
      v -> loadCars(currentPage));
  }

  private void hideError() {
    ErrorUtils.hideError(binding.layoutError);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onItemClick(Car item) {
    // TODO handle car click here
  }

  @Override public void onRefresh() {
    endlessScrollListener.resetState();
    loadCars(currentPage = START_PAGE);
  }
}