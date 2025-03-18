package com.example.deso1.nguyenthimybinh.dlu_22A1001D0036;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodItem> foodList;
    private FloatingActionButton btnAdd;

    private static final int REQUEST_ADD_FOOD = 1;
    private static final int REQUEST_EDIT_FOOD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodList = new ArrayList<>();
        foodList.add(new FoodItem("001", "Gỏi gà", 120000, R.drawable.ic_food1));
        foodList.add(new FoodItem("002", "Bò lúc lắc", 150000, R.drawable.ic_food2));
        foodList.add(new FoodItem("003", "Tôm hấp", 100000, R.drawable.ic_food3));

        foodAdapter = new FoodAdapter(this, foodList);
        recyclerView.setAdapter(foodAdapter);

        foodAdapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                FoodItem item = foodList.get(position);
                Intent intent = new Intent(MainActivity.this, EditFoodActivity.class);
                intent.putExtra("food_id", item.getId());
                intent.putExtra("food_name", item.getName());
                intent.putExtra("food_price", item.getPrice());
                intent.putExtra("food_image", item.getImage());
                intent.putExtra("food_position", position);
                startActivityForResult(intent, REQUEST_EDIT_FOOD);
            }
        });

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Thêm món ăn", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AddFoodActivity.class);
            startActivityForResult(intent, REQUEST_ADD_FOOD);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_ADD_FOOD) {

                String newId = data.getStringExtra("food_id");
                String newName = data.getStringExtra("food_name");
                int newPrice = data.getIntExtra("food_price", 0);
                int newImage = data.getIntExtra("food_image", R.drawable.ic_food_placeholder);

                FoodItem newItem = new FoodItem(newId, newName, newPrice, newImage);
                foodList.add(newItem);
                foodAdapter.notifyItemInserted(foodList.size() - 1);

            } else if (requestCode == REQUEST_EDIT_FOOD) {

                int position = data.getIntExtra("food_position", -1);
                if (position != -1) {
                    String editedName = data.getStringExtra("edited_name");
                    int editedPrice = data.getIntExtra("edited_price", 0);
                    int editedImage = data.getIntExtra("edited_image", R.drawable.ic_food_placeholder);

                    FoodItem updatedItem = foodList.get(position);
                    updatedItem.setName(editedName);
                    updatedItem.setPrice(editedPrice);
                    updatedItem.setImage(editedImage);
                    foodAdapter.notifyItemChanged(position);
                }
            }
        }
    }
}
