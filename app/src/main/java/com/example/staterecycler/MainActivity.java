package com.example.staterecycler;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    StateAdapter adapter;

    List<StateClass> data_of_state;
    TextView Active,Confirmed,Recovered,Death;
  private static final String URL = "https://data.covid19india.org/data.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Active = findViewById(R.id.active);
        Confirmed = findViewById(R.id.confirmed);
        Recovered = findViewById(R.id.recovered);
        Death = findViewById(R.id.death);

        recyclerView = findViewById(R.id.state_recycler);
        recyclerView.setHasFixedSize(true);
        data_of_state = new ArrayList<>();
        extractStateData();

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
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if(i==0){
                            Active.setText(jsonObject.getString("active".toString()));
                            Confirmed.setText(jsonObject.getString("confirmed".toString()));
                            Death.setText(jsonObject.getString("deaths".toString()));
                            Recovered.setText(jsonObject.getString("recovered".toString()));
                        }
                        else {
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
                Log.d("anError", "Something went wrong");
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }


}



//https://api.covid19india.org/data.json//


