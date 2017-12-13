package com.example.kelvin.laiofferproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RestaurantActivity extends AppCompatActivity {
    int pos = 0;
    RestaurantGridFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();

        // Todo change it to new restaurant info
        fragment = new RestaurantGridFragment();
    }

    //@Override
    public void onWindowsFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        fragment.onItemSelected(pos);
    }
}
