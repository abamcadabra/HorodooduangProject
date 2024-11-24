package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TarotLove extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarot_career);

        ImageView cardImage = findViewById(R.id.career_card);
        Button buttonPredict = findViewById(R.id.buttonPredict);

        // รับค่าจาก Intent
        int selectedCard = getIntent().getIntExtra("selectedCard", -1);
        Log.d("Debug", "Received selectedCard: " + selectedCard); // เพิ่ม Log เพื่อดูค่าที่ได้รับ

        if (selectedCard != -1) {
            // แสดงภาพที่ได้รับ
            cardImage.setImageResource(selectedCard);
        }

        buttonPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Sending imageId: " + selectedCard); // ล็อกการส่งค่าผ่าน Intent
                Intent intent = new Intent(TarotLove.this, TarotPredictionLove.class);
                intent.putExtra("imageId", selectedCard);
                startActivity(intent);
            }
        });
    }
}







