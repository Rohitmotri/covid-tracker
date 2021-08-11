package com.example.staterecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

public class StateDetail extends AppCompatActivity {
    TextView textView;
    String District = "https://api.covid19india.org/state_district_wise.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_detail);


        Intent intent = getIntent();
        String state = intent.getStringExtra("state");
        String active = intent.getStringExtra("active");

        textView = findViewById(R.id.example);

        textView.setText(state);
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
                    for (int i = 0; i< Objects.requireNonNull(jsonArray).length(); i++)
                    {
                        JSONObject district = (JSONObject) jsonArray.get(i);
                        Log.d("AZ", "onResponse: "+district);
                        Iterator<String> districtname = district.keys();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("error", "onResponse:"+e);
                    Toast.makeText(StateDetail.this, " error"+e, Toast.LENGTH_SHORT).show();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Shubham", "Something went wrong");
                Toast.makeText(StateDetail.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}