package com.example.staterecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.Viewholder> {

    Context context;
    List<DistrictClass> district_data;

    public DistrictAdapter(Context context, List<DistrictClass> district_data) {
        this.context = context;
        this.district_data = district_data;
    }

    @NonNull
    @NotNull
    @Override
    public DistrictAdapter.Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_view,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DistrictAdapter.Viewholder holder, int position) {
     DistrictClass districtClass = district_data.get(position);

     holder.districtdeaths.setText(districtClass.getDeceased());
     holder.districtrecovered.setText(districtClass.getRecovered());
     holder.districtactive.setText(districtClass.getActive());
     holder.districtconfimed.setText(districtClass.getConfirmed());
     holder.districtname.setText(districtClass.getName());
    }

    @Override
    public int getItemCount() {
        return district_data.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView districtname,districtactive,districtdeaths,districtconfimed,districtrecovered;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            districtname= itemView.findViewById(R.id.districtname);
            districtactive = itemView.findViewById(R.id.districtactive);
            districtconfimed = itemView.findViewById(R.id.districtconfirmed);
            districtrecovered  =itemView.findViewById(R.id.districtrecovered);
            districtdeaths = itemView.findViewById(R.id.districtdeaths);
        }

    }
}
