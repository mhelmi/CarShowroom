package com.bugevil.mhelmi.carshowroom.utils;

import android.util.Log;
import android.view.View;
import com.bugevil.mhelmi.carshowroom.R;
import com.bugevil.mhelmi.carshowroom.databinding.LayoutErrorBinding;
import io.reactivex.disposables.CompositeDisposable;

public class ErrorUtils {
  private static final String TAG = ErrorUtils.class.getSimpleName();

  public static void showError(LayoutErrorBinding binding,
    CompositeDisposable disposables,
    View.OnClickListener onClickListener) {
    disposables.add(
      ConnectivityUtils.isNetworkAvailable()
        .subscribe(hasInternet ->
            binding.tvNoInternet.setText(
              hasInternet ? R.string.server_error : R.string.no_internet_message)
          , t -> Log.e(TAG, "showNoInternetMessage: ", t))
    );
    binding.layoutBackgroundProgress.setVisibility(View.VISIBLE);
    binding.layoutNoInternet.setVisibility(View.VISIBLE);
    binding.tvNoInternet.setVisibility(View.VISIBLE);
    binding.btnTryAgain.setVisibility(View.VISIBLE);
    binding.btnTryAgain.setOnClickListener(onClickListener);
  }

  public static void hideError(LayoutErrorBinding binding) {
    binding.layoutBackgroundProgress.setVisibility(View.GONE);
  }
}
