package com.example.medrem;

import android.Manifest;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class AddMed extends AppCompatActivity {


    TextView timeRemainder;
    int hr, min;   //for timePicker

    ImageView ClickedMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);


        //********************** time picker starts from here
        timeRemainder = findViewById(R.id.timeRemainder);
        timeRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddMed.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hr = hourOfDay;
                                min = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, hr, min);
                                timeRemainder.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false

                );
                //displayed previous selected time
                timePickerDialog.updateTime(hr, min);
                timePickerDialog.show();
            }
        });
        //*********** upto here for time picker

        // for back  arrow button
        getSupportActionBar().setTitle("AddMed Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //********** for image picker
        if(ContextCompat.checkSelfPermission(AddMed.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddMed.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },100);
        }

        ClickedMed = findViewById(R.id.ClickedMed);
        ClickedMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Bitmap captureImage =(Bitmap)  data.getExtras().get("data");
            ClickedMed.setImageBitmap(captureImage);
            Toast.makeText(AddMed.this,"Medicine Image Updated!",Toast.LENGTH_LONG).show();
        }
    }

    //******************for back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    //***********Upto here for back arrow button




}