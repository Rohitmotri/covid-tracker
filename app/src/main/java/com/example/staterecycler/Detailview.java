package com.example.staterecycler;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Detailview extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        TextView active = (TextView) findViewById(R.id.active1);
//        TextView recovered = (TextView) findViewById(R.id.recovered1);
//        TextView death = (TextView) findViewById(R.id.deaths1);
//        TextView confirmed = (TextView) findViewById(R.id.confirmed1);
//        TextView state = (TextView) findViewById(R.id.state1);
//        Intent i = getIntent();
//         String Active=i.getStringExtra("active");
//         String Confirmed =i.getStringExtra("confirmed");
//         String Deaths=i.getStringExtra("deaths");
//         String Recovered=i.getStringExtra("recovered");
//         String State=i.getStringExtra("state");
//         active.setText(Active);
//         confirmed.setText(Confirmed);
//         death.setText(Deaths);
//         recovered.setText(Recovered);
//         state.setText(State);
    }
}
