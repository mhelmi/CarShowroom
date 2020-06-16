package com.bugevil.mhelmi.carshowroom.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectivityUtils {

  /**
   * check if the device is connected using wifi or data weather there is internet connection or not
   * it's not recommended to use this to check internet availability,
   * it's better to use isNetworkAvailable() method for this purpose.
   *
   * @return true if connected or false if not connected.
   */
  public static boolean isConnected(Context context) {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo =
        connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  /**
   * this will check the real internet connectivity via connecting to Google DNS
   * this method is the one you should use when you want to check real internet connectivity
   */
  public static Single<Boolean> isNetworkAvailable() {
    return Single.fromCallable(() -> {
      try {
        Socket socket = new Socket();
        InetSocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);
        socket.connect(socketAddress, 1500);
        socket.close();
        return true;
      } catch (IOException e) {
        return false;
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
