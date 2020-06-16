package com.bugevil.mhelmi.carshowroom.utils.viewmodel;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;

public class BaseViewModel extends ViewModel {
  private CompositeDisposable disposables = new CompositeDisposable();
  private HashMap<String, Disposable> disposablesMap = new HashMap<>();

  protected void add(Disposable disposable) {
    disposables.add(disposable);
  }

  protected void add(String TAG, Disposable disposable) {
    remove(TAG);
    disposablesMap.put(TAG, disposable);
    disposables.add(disposable);
  }

  private void remove(String TAG) {
    Disposable disposable = disposablesMap.get(TAG);
    if (disposable != null) {
      disposables.remove(disposable);
    }
  }

  protected void clearDisposables() {
    disposables.clear();
  }

  @Override protected void onCleared() {
    super.onCleared();
    disposables.dispose();
  }
}