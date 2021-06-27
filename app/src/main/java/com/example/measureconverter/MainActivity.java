package com.example.measureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText heightCm;
    private TextView heightFt;
    private TextView heightIn;
    private TextView heightInches;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        calculateBtnClickListener();
    }

    private void findViews() {
        heightCm = findViewById(R.id.edit_txt_height_cm);
        heightFt = findViewById(R.id.edit_txt_height_ft);
        heightIn = findViewById(R.id.edit_txt_height_in);
        heightInches = findViewById(R.id.edit_txt_height_inches);
        btnCalculate = findViewById(R.id.btn_calculate);
    }

    private void calculateBtnClickListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heightCm.getText().toString().trim().length() != 0) {
                    calculateHeight();
                } else {
                    Toast.makeText(MainActivity.this, "Write height in centimeters", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void calculateHeight() {
        int cm = Integer.parseInt(heightCm.getText().toString());
        int feet = 0;
        int inches;

        double ft = cm/30.48;

        if(ft < 1.0) {                          // if it's less than 30cm (1 foot)
            inches = (int) Math.round(cm/2.54); // calculate only inches
        } else {
            feet = (int) (cm/30.48);            // else calculate feet and inches
            inches = (int) Math.floor((ft-feet) * 12);
        }

        showResults(feet, inches);
    }

    private void showResults(int feet, int inches) {
        String heightFeets = String.valueOf(feet);
        String heightInches = String.valueOf(inches);
        heightFt.setText(heightFeets);
        heightIn.setText(heightInches);
    }
}