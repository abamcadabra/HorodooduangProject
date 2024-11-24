package com.example.project;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ColorPage extends AppCompatActivity {
    private Button currentButton; // เก็บปุ่มของวันที่กำลังแสดงสีอยู่

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_page);

        // สร้างการเชื่อมโยงปุ่มและ TextView ต่างๆ
        Button buttonSun = findViewById(R.id.button_sun);
        Button buttonMon = findViewById(R.id.button_mon);
        Button buttonTue = findViewById(R.id.button_tue);
        Button buttonWed = findViewById(R.id.button_wed);
        Button buttonThu = findViewById(R.id.button_thu);
        Button buttonFri = findViewById(R.id.button_fri);
        Button buttonSat = findViewById(R.id.button_sat);

        // เก็บปุ่มทั้งหมดไว้ในอาร์เรย์
        Button[] allButtons = {buttonSun, buttonMon, buttonTue, buttonWed, buttonThu, buttonFri, buttonSat};

        // กำหนด TextView ที่จะเปลี่ยนสี
        TextView[] allOutputs = {
                findViewById(R.id.output_lucky),
                findViewById(R.id.output1_work), findViewById(R.id.output2_work), findViewById(R.id.output3_work),
                findViewById(R.id.output1_money), findViewById(R.id.output2_money), findViewById(R.id.output3_money),
                findViewById(R.id.output1_love), findViewById(R.id.output2_love), findViewById(R.id.output3_love),
                findViewById(R.id.output1_fortune), findViewById(R.id.output2_fortune), findViewById(R.id.output3_fortune),
                findViewById(R.id.output1_health), findViewById(R.id.output2_health), findViewById(R.id.output3_health),
                findViewById(R.id.output1_supportive), findViewById(R.id.output2_supportive), findViewById(R.id.output3_supportive),
                findViewById(R.id.output1_unfortunate), findViewById(R.id.output2_unfortunate), findViewById(R.id.output3_unfortunate)
        };



        // สีพื้นหลังของปุ่มประจำวัน
        String sunColor = "#FF334B"; // สีของวันอาทิตย์
        String monColor = "#FFD900"; // สีของวันจันทร์
        String tueColor = "#F74397"; // สีของวันอังคาร
        String wedColor = "#06C755"; // สีของวันพุธ
        String thuColor = "#FF6F36"; // สีของวันพฤหัสบดี
        String friColor = "#40B6FF"; // สีของวันศุกร์
        String satColor = "#8556E3"; // สีของวันเสาร์

        // สร้างสีสำหรับแต่ละวัน
        String[] sunColors = {"#FF334B",
                "#F68A94", "#FF334B", "#F24500",
                "#000000", "#3E3E3E", "#BD9FF5",
                "#06C755", "#85101E", "#64E8A4",
                "#562E5E", "#CAC9C9", "#949494",
                "#FFFC33", "#FFE8C2", "#FEFEFE",
                "#1E1F1E", "#FFD900", "#483928",
                "#40B6FF", "#32457A", "#78CBFF"}; // ตัวอย่างสีสำหรับวันอาทิตย์
        String[] monColors = {"#FFD900",
                "#06C755", "#FFFC33", "#187A41",
                "#FFE8C2", "#F5F2F3", "#F24500",
                "#000000", "#3E3E3E", "#BD9FF5",
                "#A7855F", "#F77911", "#FFDD80",
                "#F68A94", "#F24500", "#EBCCDD",
                "#40B6FF", "#32457A", "#78CBFF",
                "#FF334B", "#85101E", "#C9162B"};
        String[] tueColors = {"#F74397",
                "#000000", "#3E3E3E", "#BD9FF5",
                "#1E1F1E", "#FFD900", "#483928",
                "#A7855F", "#F77911", "#F24500",
                "#F68A94", "#CAC9C9", "#949494",
                "#06C755", "#64E8A4", "#187A41",
                "#FF334B", "#85101E", "#C9162B",
                "#FFFC33", "#FFE8C2", "#FEFEFE"};
        String[] wedColors = {"#AED31D",
                "#A7855F", "#F77911", "#FFDD80",
                "#40B6FF", "#32457A", "#78CBFF",
                "#1E1F1E", "#FFD900", "#483928",
                "#06C755", "#64E8A4", "#187A41",
                "#000000", "#3E3E3E", "#BD9FF5",
                "#FFFC33", "#FFE8C2", "#FEFEFE",
                "#F68A94", "#EBCCDD", "#F74397"};
        String[] thuColors = {"#FF6F36",
                "#40B6FF", "#32457A", "#78CBFF",
                "#FFFC33", "#FFE8C2", "#FEFEFE",
                "#FF334B", "#85101E", "#C9162B",
                "#A7855F", "#F77911", "#FFDD80",
                "#1E1F1E", "#FFD900", "#483928",
                "#06C755", "#64E8A4", "#187A41",
                "#000000", "#3E3E3E", "#BD9FF5"};
        String[] friColors = {"#10A1A1",
                "#FFFC33", "#FFE8C2", "#FEFEFE",
                "#06C755", "#64E8A4", "#187A41",
                "#F68A94", "#EBCCDD", "#F74397",
                "#40B6FF", "#32457A", "#78CBFF",
                "#FF334B", "#85101E", "#C9162B",
                "#A7855F", "#F77911", "#F24500",
                "#1E1F1E", "#FFD900", "#483928"};
        String[] satColors = {"#8556E3",
                "#1E1F1E", "#FFD900", "#483928",
                "#FF334B", "#85101E", "#C9162B",
                "#40B6FF", "#32457A", "#78CBFF",
                "#F2CED2", "#FF334B", "#85111F",
                "#A7855F", "#F77911", "#F24500",
                "#F68A94", "#EBCCDD", "#F74397",
                "#CCE373", "#187A41", "#64E8A4"};


// ตั้งค่าดีฟอลต์ตามวันปัจจุบัน
        int currentDay = getCurrentDayOfWeek();
        switch (currentDay) {
            case Calendar.SUNDAY:
                highlightButton(buttonSun, allButtons, sunColor);
                changeColors(allOutputs, sunColors);
                break;
            case Calendar.MONDAY:
                highlightButton(buttonMon, allButtons, monColor);
                changeColors(allOutputs, monColors);
                break;
            case Calendar.TUESDAY:
                highlightButton(buttonTue, allButtons, tueColor);
                changeColors(allOutputs, tueColors);
                break;
            case Calendar.WEDNESDAY:
                highlightButton(buttonWed, allButtons, wedColor);
                changeColors(allOutputs, wedColors);
                break;
            case Calendar.THURSDAY:
                highlightButton(buttonThu, allButtons, thuColor);
                changeColors(allOutputs, thuColors);
                break;
            case Calendar.FRIDAY:
                highlightButton(buttonFri, allButtons, friColor);
                changeColors(allOutputs, friColors);
                break;
            case Calendar.SATURDAY:
                highlightButton(buttonSat, allButtons, satColor);
                changeColors(allOutputs, satColors);
                break;
        }

        // กำหนดการทำงานของปุ่ม
        buttonSun.setOnClickListener(view -> {
            highlightButton(buttonSun, allButtons, sunColor);
            changeColors(allOutputs, sunColors);
        });

        buttonMon.setOnClickListener(view -> {
            highlightButton(buttonMon, allButtons, monColor);
            changeColors(allOutputs, monColors);
        });

        buttonTue.setOnClickListener(view -> {
            highlightButton(buttonTue, allButtons, tueColor);
            changeColors(allOutputs, tueColors);
        });

        buttonWed.setOnClickListener(view -> {
            highlightButton(buttonWed, allButtons, wedColor);
            changeColors(allOutputs, wedColors);
        });

        buttonThu.setOnClickListener(view -> {
            highlightButton(buttonThu, allButtons, thuColor);
            changeColors(allOutputs, thuColors);
        });

        buttonFri.setOnClickListener(view -> {
            highlightButton(buttonFri, allButtons, friColor);
            changeColors(allOutputs, friColors);
        });

        buttonSat.setOnClickListener(view -> {
            highlightButton(buttonSat, allButtons, satColor);
            changeColors(allOutputs, satColors);
        });

        ImageView img = findViewById(R.id.imageView3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Syamsi activity
                Intent intent = new Intent(ColorPage.this, SelectPage.class);
                startActivity(intent);
            }
        }  );
    }

    private int getCurrentDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private void highlightButton(Button buttonToHighlight, Button[] allButtons, String highlightColor) {
        // รีเซ็ตสีของปุ่มทั้งหมด
        for (Button button : allButtons) {
            button.setBackgroundColor(Color.LTGRAY); // สีพื้นฐาน
            button.setTextColor(Color.BLACK); // สีข้อความพื้นฐาน
        }

        // ตั้งค่าปุ่มที่ถูกเลือก
        buttonToHighlight.setBackgroundColor(Color.parseColor(highlightColor)); // สีพื้นหลังประจำวัน
        buttonToHighlight.setTextColor(Color.WHITE); // สีข้อความสำหรับปุ่มที่ถูกเลือก
        currentButton = buttonToHighlight; // อัปเดตปุ่มที่กำลังเลือก
    }

    private void changeColors(TextView[] textViews, String[] colors) {
        // เปลี่ยนสี TextView ตามสีที่กำหนด
        for (int i = 0; i < textViews.length && i < colors.length; i++) {
            textViews[i].setBackgroundColor(Color.parseColor(colors[i]));
        }
    }

}