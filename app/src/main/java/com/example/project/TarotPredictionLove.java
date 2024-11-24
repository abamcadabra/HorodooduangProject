package com.example.project;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TarotPredictionLove extends AppCompatActivity {

    private TarotDataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarot_perdiction);

        try {
            // เชื่อมโยง UI
            ImageView predictionCardImage = findViewById(R.id.prediction_card);
            TextView predictionCardName = findViewById(R.id.card_name);
            predictionCardName.setPaintFlags(predictionCardName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            TextView predictionDescription = findViewById(R.id.card_description);
            TextView predictionAdvice = findViewById(R.id.card_advice);

            // รับ imageId จาก Intent
            int selectedCard = getIntent().getIntExtra("imageId", -1);
            if (selectedCard == -1) {
                Log.e("Error", "Invalid imageId received");
                return;
            }

            // สร้างตัวช่วยฐานข้อมูล
            databaseHelper = new TarotDataBaseHelper(this);
            predictionCardImage.setImageResource(selectedCard);

            // ตรวจสอบภาษาปัจจุบัน
            String language = Locale.getDefault().getLanguage();
            if (!language.equals("th") && !language.equals("en")) {
                language = "en"; // กำหนดค่าเริ่มต้นเป็นภาษาอังกฤษ
            }

            // ดึงข้อมูลไพ่ตาม imageId
            List<Map<String, String>> cards = databaseHelper.getCardByImageIdLove(selectedCard, language);

            // แสดงผลข้อมูลใน UI
            if (!cards.isEmpty()) {
                Map<String, String> card = cards.get(0); // ใช้ข้อมูลใบแรก
                predictionCardName.setText(card.get("name"));
                predictionDescription.setText(card.get("description"));
                predictionAdvice.setText(card.get("advice"));
            } else {
                Log.e("Error", "No card data found");
                predictionCardName.setText("Not Found");
                predictionDescription.setText("No Description");
                predictionAdvice.setText("No Advice");
            }
        } catch (Exception e) {
            Log.e("Error", "Exception in TarotPredictionCareer", e);
        }

        // ปุ่มย้อนกลับ
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarotPredictionLove.this, TarotTopic.class);
                startActivity(intent);
            }
        });
    }
}
