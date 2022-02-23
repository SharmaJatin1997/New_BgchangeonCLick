package com.app.bgchangeonclick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    View screenView;
    int[] back_images;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back_images = new int[]{R.drawable.d1, R.drawable.d2,
                R.drawable.d3, R.drawable.d4};
        button = findViewById(R.id.Button);
        screenView = findViewById(R.id.relative_layout);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        if (sh != null) {
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), sh.getInt("age", R.drawable.d1)));
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int array_length = back_images.length;
                Random random = new Random();
                // generation of random number
                int random_number = random.nextInt(array_length);

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putInt("age", back_images[random_number]);
                myEdit.apply();
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), back_images[random_number]));
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        mHandler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Update the value background thread to UI thread
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(i);
                    }
                });
            }
        }).start();

    }
}