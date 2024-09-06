package com.example.amazonapp.MenuFiles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amazonapp.HomeActivity;
import com.example.amazonapp.R;

public class BaseActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.i("MyTag", "This is an informational message");
        radioGroup1= (RadioGroup) findViewById(R.id.radioGroup1);
        home = (RadioButton) findViewById(R.id.bottom_home);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("RadioGroup", "onCheckedChanged called with ID: " + checkedId); // Debugging log

                Intent in;

                if (checkedId == R.id.bottom_home) {
                    Log.i("Navigation", "Navigating to HomeActivity");
                    in = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);

                } else if (checkedId == R.id.bottom_addprod) {
                    Log.i("Navigation", "Navigating to AddProduct");
                    in = new Intent(getBaseContext(), AddProduct.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);

                } else if (checkedId == R.id.bottom_search) {
                    Log.i("Navigation", "Navigating to SearchActivity");
                    in = new Intent(getBaseContext(), SearchActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);

                } else if (checkedId == R.id.bottom_cart) {
                    Log.i("Navigation", "Navigating to CartActivity");
                    in = new Intent(getBaseContext(), CartActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);

                } else if (checkedId == R.id.bottom_profile) {
                    Log.i("Navigation", "Navigating to ProfileActivity");
                    in = new Intent(getBaseContext(), ProfileActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);

                } else {
                    Log.w("Navigation", "No matching RadioButton found for checkedId: " + checkedId);
                }
            }
        });
    }
}