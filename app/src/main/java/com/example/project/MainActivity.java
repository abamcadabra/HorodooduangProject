package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    MediaPlayer mediaPlayer;
    boolean isPlaying = true; // Start with the song playing
    private boolean isExitPressedOnce = false; // To handle double-tap logic
    private Handler doubleTapHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectPage.class);
                startActivity(intent);
            }
        });
        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExitPressedOnce) {
                    // Exit the app on double-tap
                    finishAffinity();
                } else {
                    // First tap: Show alert dialog
                    showExitConfirmationDialog();
                    isExitPressedOnce = true;
                    doubleTapHandler.postDelayed(() -> isExitPressedOnce = false, 2000); // Reset after 2 seconds
                }
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        // แทนที่ "your_audio_file" ด้วยชื่อไฟล์เสียงของคุณในโฟลเดอร์ res/raw
        mediaPlayer.setLooping(true); // ตั้งค่าให้เล่นซ้ำ
        mediaPlayer.start();

        img = findViewById(R.id.imageView2);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isPlaying) {
                    // Pause the song if it's playing
                    mediaPlayer.pause();
                    isPlaying = false;
                } else {
                    // Start the song if it's not playing
                    mediaPlayer.start();
                    isPlaying = true;
                }
                return false;
            }
        });
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to leave this page? Unsaved work might be lost.")
                .setPositiveButton("Yes, go back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed(); // Navigate to the previous page
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Release MediaPlayer resources
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause(); // หยุดเพลงชั่วคราวเมื่อแอปถูกพัก
            isPlaying = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !isPlaying) {
            mediaPlayer.start(); // เล่นเพลงต่อเมื่อแอปกลับมาทำงาน
            isPlaying = true;
        }
    }

}

