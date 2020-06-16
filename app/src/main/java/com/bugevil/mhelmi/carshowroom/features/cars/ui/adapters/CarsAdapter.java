package com.bugevil.mhelmi.carshowroom.features.cars.ui.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bugevil.mhelmi.carshowroom.R;
import com.bugevil.mhelmi.carshowroom.databinding.ItemCarBinding;
import com.bugevil.mhelmi.carshowroom.features.cars.domain.models.Car;
import com.bumptech.glide.Glide;
import java.util.*;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {

  private OnCarClickListener onItemClickListener;
  private List<Car> itemList = new ArrayList<>();

  public CarsAdapter(OnCarClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public void setCarList(List<Car> itemList) {
    this.itemList = itemList;
    notifyDataSetChanged();
  }

  public void addAllCarList(List<Car> itemList) {
    this.itemList.addAll(itemList);
    notifyDataSetChanged();
  }

  public void clear() {
    itemList.clear();
    notifyDataSetChanged();
  }

  @NonNull @Override
  public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemCarBinding binding =
      ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new CarViewHolder(binding);
  }

  @Override public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
    holder.bindItem(itemList.get(position));
  }

  @Override public int getItemCount() {
    return itemList != null ? itemList.size() : 0;
  }

  class CarViewHolder extends RecyclerView.ViewHolder {

    ItemCarBinding binding;
    private Context context;

    public CarViewHolder(@NonNull ItemCarBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      context = itemView.getContext();
    }

    private void bindItem(Car item) {
      Glide.with(context)
        .load(item.getImageUrl())
        .placeholder(R.drawable.ic_car_placeholder)
        .into(binding.ivCarPhoto);
      binding.tvBrand.setText(item.getBrand());
      binding.tvIsUsedValue.setText(item.isUsedText(context));
      binding.tvConstructionYearValue.setText(item.getConstructionYear());
      boolean constructionYearIsEmpty = TextUtils.isEmpty(item.getConstructionYear());
      binding.tvConstructionYearValue.setVisibility(
        constructionYearIsEmpty ? View.GONE : View.VISIBLE);
      binding.tvConstructionYear.setVisibility(constructionYearIsEmpty ? View.GONE : View.VISIBLE);
      itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
    }
  }

  public interface OnCarClickListener {
    void onItemClick(Car item);
  }
}