package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TarotBackCard2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarot_back);

        ImageView backCard = findViewById(R.id.backcard);

        // Array ของรูปภาพ (อาจเป็น resource ID)
        int[] cardImages = {
                R.drawable.fool_0,
                R.drawable.magician_1,
                R.drawable.high_priestess_2,
                R.drawable.the_empress_3,
                R.drawable.the_emperor_4,
                R.drawable.the_hierophant_5,
                R.drawable.the_lovers_6,
                R.drawable.the_chariot_7,
                R.drawable.strength_8,
                R.drawable.the_hermit_9,
                R.drawable.wheel_of_fortune_10,
                R.drawable.justice_11,
                R.drawable.the_hanged_one_12,
                R.drawable.death_13,
                R.drawable.temperance_14,
                R.drawable.temperance_14,
                R.drawable.the_devil_15,
                R.drawable.the_tower_16,
                R.drawable.the_star_17,
                R.drawable.the_moon_18,
                R.drawable.the_sun_19,
                R.drawable.judgement_20,
                R.drawable.the_world_21
        };

        backCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Random รูปภาพ
                int randomIndex = new Random().nextInt(cardImages.length);
                int selectedCard = cardImages[randomIndex];

                // ส่งไปหน้า TarotCareer
                Intent intent = new Intent(TarotBackCard2.this, TarotLove.class);
                intent.putExtra("selectedCard", selectedCard);
                startActivity(intent);
            }
        });

    }
}


