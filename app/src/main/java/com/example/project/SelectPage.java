package com.example.project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_page);

        // Initialize the button
        Button buttonSyamsi = findViewById(R.id.button_syamsi);
        Button buttonTarot = findViewById(R.id.button_tarot);
        Button buttonColor = findViewById(R.id.button_color);

        // Set up the click listener
        buttonSyamsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(SelectPage.this, Syamsi.class);
                startActivity(intent);
            }
        });

        buttonTarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(SelectPage.this, TarotTopic.class);
                startActivity(intent);
            }
        });
        buttonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(SelectPage.this, ColorPage.class);
                startActivity(intent);
            }
        });

        ImageView img = findViewById(R.id.imageView3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(SelectPage.this, MainActivity.class);
                startActivity(intent);
            }
        }  );



    }

}
