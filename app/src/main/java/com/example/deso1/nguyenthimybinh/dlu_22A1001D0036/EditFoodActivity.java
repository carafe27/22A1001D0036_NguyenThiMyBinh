package com.example.deso1.nguyenthimybinh.dlu_22A1001D0036;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EditFoodActivity extends AppCompatActivity {
    private EditText edtName, edtPrice;
    private ImageView imgFood;
    private Button btnSave;
    private int foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        imgFood = findViewById(R.id.imgFood);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        if (intent != null) {
            edtName.setText(intent.getStringExtra("food_name"));
            edtPrice.setText(intent.getStringExtra("food_price"));
            foodImage = intent.getIntExtra("food_image", R.drawable.ic_food_placeholder);
            imgFood.setImageResource(foodImage);
        }

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("edited_name", edtName.getText().toString());
            resultIntent.putExtra("edited_price", edtPrice.getText().toString());
            resultIntent.putExtra("edited_image", foodImage);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
