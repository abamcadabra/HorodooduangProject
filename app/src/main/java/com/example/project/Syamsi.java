package com.example.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import java.util.Random;

public class Syamsi extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private PowerManager.WakeLock wl;

    private static final int shake_threshold = 10;  // ลดค่าการตรวจจับให้ไวขึ้น
    private Boolean shown_dialog = false;
    private SensorInfo sensor_info = new SensorInfo();

    private Thread sensorThread; // Thread สำหรับการตรวจสอบเซ็นเซอร์


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.syamsi);

        // กำหนดการตั้งค่า WindowInsets เพื่อการจัดการขอบหน้าจอ
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // กำหนด SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Initializing PowerManager and WakeLock
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            wl = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakeLockTag");
        }

        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .asGif()
                .load(R.raw.ss) // โหลดไฟล์จาก res/raw
                .override(750, 1000) // กำหนดขนาดเป็น 750x1000 pixels
                .into(imageView);

        ImageView img = findViewById(R.id.imageView3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(Syamsi.this, SelectPage.class);
                startActivity(intent);
            }
        }  );

    }


    @Override
    protected void onResume() {
        super.onResume();
        // ลงทะเบียน Listener สำหรับ Accelerometer
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        // Ensure WakeLock is acquired if it's not already
        if (wl != null && !wl.isHeld()) {
            wl.acquire();
        }

        // สร้างและเริ่มต้น Thread สำหรับการตรวจสอบเซ็นเซอร์
        sensorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    checkForShake();  // ฟังก์ชันที่เปรียบเทียบค่า
                    try {
                        Thread.sleep(100);  // รอระยะเวลาสั้นๆ ก่อนตรวจสอบใหม่
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();  // ถ้าเกิดการหยุดการทำงาน
                    }
                }
            }
        });
        sensorThread.start();  // เริ่มต้น Thread
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ยกเลิกการฟังข้อมูลจากเซ็นเซอร์
        sensorManager.unregisterListener(this);

        // Release WakeLock if it's held
        if (wl != null && wl.isHeld()) {
            wl.release();
        }

        // หยุด Thread เมื่อไม่ใช้งานแล้ว
        if (sensorThread != null && sensorThread.isAlive()) {
            sensorThread.interrupt();  // หยุดการทำงานของ Thread
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // ตรวจสอบว่าเซ็นเซอร์คือ Accelerometer
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // อัปเดตค่าจากเซ็นเซอร์
            sensor_info.accX = event.values[0];
            sensor_info.accY = event.values[1];
            sensor_info.accZ = event.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ไม่มีการใช้งาน
    }

    // ฟังก์ชันตรวจสอบการเขย่า
    private void checkForShake() {
        if ((Math.abs(sensor_info.accX) > shake_threshold) ||
                (Math.abs(sensor_info.accY) > shake_threshold) ||
                (Math.abs(sensor_info.accZ) > shake_threshold)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            });
        }
    }

    // ฟังก์ชันแสดง Dialog
    public void showDialog() {
        if (!shown_dialog) {
            shown_dialog = true;

            // สุ่มหมายเลขระหว่าง 1-12
            Random random = new Random();
            int randomNumber = random.nextInt(12) + 1;

            String randomMessage = getRandomMessage(randomNumber);

            final AlertDialog.Builder viewDialog = new AlertDialog.Builder(this);
            viewDialog.setIcon(android.R.drawable.btn_star_big_on);

            viewDialog.setTitle(String.valueOf(randomNumber));
            viewDialog.setMessage(randomMessage);
            viewDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    shown_dialog = false;  // Reset flag after dismissing the dialog
                }
            });
            viewDialog.show();
        }
    }

    private String getRandomMessage(int number) {
        switch (number) {
            case 1:
                return getString(R.string.fortune_message_1);
            case 2:
                return getString(R.string.fortune_message_2);
            case 3:
                return getString(R.string.fortune_message_3);
            case 4:
                return getString(R.string.fortune_message_4);
            case 5:
                return getString(R.string.fortune_message_5);
            case 6:
                return getString(R.string.fortune_message_6);
            case 7:
                return getString(R.string.fortune_message_7);
            case 8:
                return getString(R.string.fortune_message_8);
            case 9:
                return getString(R.string.fortune_message_9);
            case 10:
                return getString(R.string.fortune_message_10);
            case 11:
                return getString(R.string.fortune_message_11);
            case 12:
                return getString(R.string.fortune_message_12);
            default:
                return getString(R.string.fortune_message_default);
        }
    }



    // คลาสสำหรับเก็บข้อมูลจากเซ็นเซอร์
    static class SensorInfo {
        float accX, accY, accZ;
    }
}
