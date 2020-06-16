package com.bugevil.mhelmi.carshowroom.utils.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {

  private Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap;

  @Inject
  public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    this.providerMap = providerMap;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) providerMap.get(modelClass).get();
  }
}