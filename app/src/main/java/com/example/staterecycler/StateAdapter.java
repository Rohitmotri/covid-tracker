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
//                Toast.makeText(context, "position = "+String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,StateDetail.class);
                intent.putExtra("state",name);
                intent.putExtra("active",active);
                itemView.getContext().startActivity(intent);
            }
            catch (Exception e){
                Toast.makeText(context, "error while loading"+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

//    LayoutInflater inflater;
//    List<StateClass> Statedata;
//
//    public StateAdapter(Context ctx,List<StateClass> Statedata){
//        this.inflater = LayoutInflater.from(ctx);
//        this.Statedata = Statedata;
//    }
//
//    @NonNull
//    @Override
//    public StateAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.state_view,parent,false);
//        return new Viewholder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull StateAdapter.Viewholder holder, int position) {
//        StateClass statedata = Statedata.get(position);
//        holder.statename.setText(Statedata.get(position).getState());
//        holder.stateconfirmed.setText(Statedata.get(position).getConfirmed());
//        holder.staterecovered.setText(Statedata.get(position).getRecovered());
//        holder.statedeaths.setText(Statedata.get(position).getDeaths());
//        holder.stateactive.setText(Statedata.get(position).getActive());
//        holder.itemView.setOnClickListener((view) ->{
//            Intent intent = new Intent()
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return Statedata.size();
//    }
//
//    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView statename,stateactive,statedeaths,stateconfirmed,staterecovered;
//        public Viewholder(@NonNull View itemView) {
//            super(itemView);
//            statename = itemView.findViewById(R.id.statename);
//            stateactive = itemView.findViewById(R.id.stateactive);
//            statedeaths = itemView.findViewById(R.id.statedeaths);
//            staterecovered = itemView.findViewById(R.id.staterecovered);
//            stateconfirmed = itemView.findViewById(R.id.stateconfirmed);
//            itemView.setOnClickListener((View.OnClickListener) this);
//
//
//        }
//
//       @Override
//       public void onClick(View v) {
//           int position = this.getAbsoluteAdapterPosition();
//           StateClass state = Statedata.get(position);
//           String name = state.getState();
//            String active = state.getActive();
//            String deaths = state.getDeaths();
//            Toast.makeText(itemView.getContext(), "clicked position = "+ String.valueOf(position)+ "state = "+name+"active= "+active+" deaths= "+deaths, Toast.LENGTH_SHORT).show();
//           Intent intent = new Intent(itemView.getContext(), StateDetail.class);
////            intent.putExtra("active",state.getActive());
////           intent.putExtra("recovered",state.getRecovered());
////           intent.putExtra("deaths",state.getDeaths());
////           intent.putExtra("confirmed",state.getConfirmed());
////           intent.putExtra("state",state.getState());
//            itemView.getContext().startActivity(intent);
//
//        }
    //}
}