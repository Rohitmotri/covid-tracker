package com.example.staterecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    StateAdapter adapter;
    Button button;
    List<StateClass> data_of_state;
    TextView Active,Confirmed,Recovered,Death;
    String URL = "https://api.covid19india.org/data.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Active = findViewById(R.id.active);
        Confirmed = findViewById(R.id.confirmed);
        Recovered = findViewById(R.id.recovered);
        Death = findViewById(R.id.death);

        button=findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActiviy();
            }
        });

        recyclerView = findViewById(R.id.state_recycler);
        recyclerView.setHasFixedSize(true);
        data_of_state = new ArrayList<>();
        extractStateData();

    }

    private void openActiviy() {
        Intent intent = new Intent(this, StateDetail.class);
        startActivity(intent);
    }

    private void extractStateData() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("statewise");
                    for(int i=0;i<jsonArray.length();i++){
                        if(i==0){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Active.setText(jsonObject.getString("active".toString()));
                            Confirmed.setText(jsonObject.getString("confirmed".toString()));
                            Death.setText(jsonObject.getString("deaths".toString()));
                            Recovered.setText(jsonObject.getString("recovered".toString()));
                        }
                        else {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            StateClass stateClass = new StateClass();
                            String name = jsonObject.getString("state");
                            Log.d("name", "onResponse: " + name);
                            stateClass.setState(jsonObject.getString("state".toString()));
                            stateClass.setActive(jsonObject.getString("active".toString()));
                            stateClass.setDeaths(jsonObject.getString("deaths".toString()));
                            stateClass.setRecovered(jsonObject.getString("recovered".toString()));
                            stateClass.setConfirmed(jsonObject.getString("confirmed".toString()));
                            data_of_state.add(stateClass);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter = new StateAdapter(getApplicationContext(),data_of_state);
                recyclerView.setAdapter(adapter);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Shubham", "Something went wrong");
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }


}



//https://api.covid19india.org/data.json//


