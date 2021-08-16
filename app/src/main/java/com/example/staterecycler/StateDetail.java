package com.example.staterecycler;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class StateDetail extends AppCompatActivity {
    TextView textView,dConfirmed,dRecovered,dActive,dDeaths;
    String District = "https://data.covid19india.org/state_district_wise.json";
    RecyclerView districtRecycler;
    List<String> nameOfDistrict= new ArrayList<String>();
    List<DistrictClass> data_of_district;
    DistrictAdapter districtAdapter;
    nameAdapter nameAdapter ;
    ConcatAdapter adapter;

    ArrayList<String> namedist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_detail);

        Intent intent = getIntent();
        String state = intent.getStringExtra("state");
        String active = intent.getStringExtra("active");

        textView = findViewById(R.id.statename);
        dActive = findViewById(R.id.active_s);
        dRecovered = findViewById(R.id.recovered_s);
        dConfirmed = findViewById(R.id.confirmed_s);
        dDeaths = findViewById(R.id.death_s);

        textView.setText(state);
        dDeaths.setText(intent.getStringExtra("deaths"));
        dActive.setText(intent.getStringExtra("active"));
        dRecovered.setText(intent.getStringExtra("recovered"));
        dConfirmed.setText(intent.getStringExtra("confirmed"));

        districtRecycler = findViewById(R.id.district_recycler);
        districtRecycler.setHasFixedSize(true);
        data_of_district = new ArrayList<>();

        extractDistrictdata();
    }
    private void extractDistrictdata() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                District, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject jsonObject = (JSONObject) response.getJSONObject(getIntent().getStringExtra("state")).get("districtData");
                    JSONArray jsonArray = jsonObject.toJSONArray(jsonObject.names());
                    Iterator<?> iterator = jsonObject.keys();

                    while(iterator.hasNext())
                    {
                        String key = String.valueOf(iterator.next());
                        namedist.add(key);
                        String District = Arrays.toString(key.split("name: "));
//                        for(int i=0;i<key.length();i++)
//                        {
//                            districtClass.setName(key[i]);
//                        }
                        Log.d("district name", "district"+ key );

//                        nameOfDistrict.add(key);

                    }

                    for (int i = 0; i< Objects.requireNonNull(jsonArray).length(); i++)
                    {
                        JSONObject district = (JSONObject) jsonArray.get(i);
                        DistrictClass districtClass = new DistrictClass();
                        districtClass.setName(namedist.get(i));
                        Log.d("districtname", "onResponse: "+namedist.get(i));

                        districtClass.setActive(district.getString("active".toString()));
                        districtClass.setConfirmed(district.getString("confirmed".toString()));
                        districtClass.setRecovered(district.getString("recovered".toString()));
                        districtClass.setDeceased(district.getString("deceased".toString()));
                        data_of_district.add(districtClass);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("error", "onResponse:"+e);
                    Toast.makeText(StateDetail.this, " error"+e, Toast.LENGTH_SHORT).show();
                }

                districtRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                districtAdapter = new DistrictAdapter(getApplicationContext(),data_of_district);
//                nameAdapter = new nameAdapter(getApplicationContext(), (ArrayList<String>) nameOfDistrict);
//                adapter = new ConcatAdapter(districtAdapter,nameAdapter);
                Log.d("debugg", "onResponse: "+data_of_district);
                districtRecycler.setAdapter(districtAdapter);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Shubham", "Something went wrong");
                Toast.makeText(StateDetail.this, "district problem", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}