package com.example.deso1.nguyenthimybinh.dlu_22A1001D0036;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public FoodAdapter(MainActivity mainActivity, List<FoodItem> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem item = foodList.get(position);
        holder.foodName.setText(item.getName());
        holder.foodPrice.setText(item.getPrice());
        holder.foodImage.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements com.example.deso1.nguyenthimybinh.dlu_22A1001D0036.FoodViewHolder {
        TextView foodName, foodPrice;
        ImageView foodImage, editIcon;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodImage = itemView.findViewById(R.id.foodImage);
            editIcon = itemView.findViewById(R.id.editIcon);

            editIcon.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(position);
                    }
                }
            });
        }
        @Override
        public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
            FoodItem item = foodList.get(position);
            holder.foodName.setText(item.getName());
            holder.foodPrice.setText(String.valueOf(item.getPrice()));
            holder.foodImage.setImageResource(item.getImage());

            holder.editIcon.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(position);
                }
            });
        }
    }
}
