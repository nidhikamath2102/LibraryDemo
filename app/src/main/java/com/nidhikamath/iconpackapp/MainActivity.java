package com.nidhikamath.iconpackapp;

import android.os.Bundle;
import android.widget.Toast;

import com.ehom.iconpackapp.Icons;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int result = Icons.plus(2, 3);
        Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
    }
}