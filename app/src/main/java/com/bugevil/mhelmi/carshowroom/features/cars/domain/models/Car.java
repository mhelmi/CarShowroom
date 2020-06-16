package com.bugevil.mhelmi.carshowroom.features.cars.domain.models;

import android.content.Context;
import androidx.annotation.Keep;
import com.bugevil.mhelmi.carshowroom.R;
import com.google.gson.annotations.SerializedName;

@Keep
public class Car {
  @SerializedName("id")
  private int id;
  @SerializedName("brand")
  private String brand;
  @SerializedName("constructionYear")
  private String constructionYear;
  @SerializedName("isUsed")
  private boolean isUsed;
  @SerializedName("imageUrl")
  private String imageUrl;

  public int getId() {
    return id;
  }

  public String getBrand() {
    return brand;
  }

  public String getConstructionYear() {
    return constructionYear;
  }

  public boolean isUsed() {
    return isUsed;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String isUsedText(Context context) {
    return isUsed ? context.getString(R.string.used) : context.getString(R.string.is_new);
  }
}
