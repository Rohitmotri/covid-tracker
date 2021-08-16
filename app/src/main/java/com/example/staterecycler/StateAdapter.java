package com.example.staterecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.Viewholder>{

    private Context context;
    private List<StateClass> statedata;

    public StateAdapter(Context applicationContext, List<StateClass> data_of_state) {
        this.context= applicationContext;
        this.statedata = data_of_state;
    }

    @Override
    public StateAdapter.@NotNull Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_view,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StateAdapter.Viewholder holder, int position) {
        StateClass stateClass = statedata.get(position);

        holder.statename.setText(stateClass.getState());
        holder.stateactive.setText(stateClass.getActive());
        holder.statedeaths.setText(stateClass.getDeaths());
        holder.stateconfirmed.setText(stateClass.getConfirmed());
        holder.staterecovered.setText(stateClass.getRecovered());
    }

    @Override
    public int getItemCount() {
        return statedata.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView statename,stateactive,statedeaths,stateconfirmed,staterecovered;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            statename = itemView.findViewById(R.id.statename);
            stateactive = itemView.findViewById(R.id.stateactive);
            statedeaths = itemView.findViewById(R.id.statedeaths);
            staterecovered = itemView.findViewById(R.id.staterecovered);
            stateconfirmed = itemView.findViewById(R.id.stateconfirmed);
            statename.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try{
                int position = getAbsoluteAdapterPosition();
                StateClass stateClass = statedata.get(position);
                String name = stateClass.getState();
                String active = stateClass.getActive();
                String deaths = stateClass.getDeaths();
                Intent intent = new Intent(context,StateDetail.class);
                intent.putExtra("state",name);
                intent.putExtra("active",active);
                intent.putExtra("deaths",stateClass.getDeaths());
                intent.putExtra("confirmed",stateClass.getConfirmed());
                intent.putExtra("recovered",stateClass.getDeaths());
                itemView.getContext().startActivity(intent);
            }
            catch (Exception e){
                Toast.makeText(context, "error while loading"+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

}