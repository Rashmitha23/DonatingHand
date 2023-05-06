package com.example.food_donation_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Search_Food extends AppCompatActivity {

    CardView ccok,roow,oother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        ccok=findViewById(R.id.cook);
        roow=findViewById(R.id.row);
        oother=findViewById(R.id.searchother);

        ccok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search_Cook.class);
                startActivity(intent);
            }
        });
        roow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search_Row.class);
                startActivity(intent);
            }
        });
        oother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search_Other.class);
                startActivity(intent);
            }
        });
    }
}