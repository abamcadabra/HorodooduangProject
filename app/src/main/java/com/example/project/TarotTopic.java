package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TarotTopic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarot);

        // Initialize the button
        Button buttonCareer = findViewById(R.id.button_work);

        // Set up the click listener
        buttonCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarotTopic.this, TarotBackCard.class);
                startActivity(intent);
            }
        });
        Button buttonMoney = findViewById(R.id.button_money);

        // Set up the click listener
        buttonMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarotTopic.this, TarotBackCard1.class);
                startActivity(intent);
            }
        });

        Button buttonLove = findViewById(R.id.button_love);

        // Set up the click listener
        buttonLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarotTopic.this, TarotBackCard2.class);
                startActivity(intent);
            }
        });

        Button buttonHealth = findViewById(R.id.button_health);

        // Set up the click listener
        buttonHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarotTopic.this, TarotBackCard3.class);
                startActivity(intent);
            }
        });

        Button buttonLuck = findViewById(R.id.button_luck);

        // Set up the click listener
        buttonLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TarotTopic.this, TarotBackCard4.class);
                startActivity(intent);
            }
        });
        ImageView img = findViewById(R.id.imageView3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(TarotTopic.this, SelectPage.class);
                startActivity(intent);
            }
        }  );
    }
}
