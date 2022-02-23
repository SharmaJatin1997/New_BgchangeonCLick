package com.app.bgchangeonclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    View screenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        screenView = findViewById(R.id.relative_layout);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        if (sh != null) {
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), sh.getInt("age", R.drawable.d1)));
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }
}