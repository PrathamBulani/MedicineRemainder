package com.example.medrem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCanceledOnTouchOutside(true);
    }

    public void openActivityAddMed(View view) {
        Intent intent=new Intent(this,AddMed.class);
        startActivity(intent);
    }
}