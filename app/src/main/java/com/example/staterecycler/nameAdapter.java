package com.example.staterecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class nameAdapter extends RecyclerView.Adapter<nameAdapter.Viewholder>{
    public Context context;
    public ArrayList<String> data;

    public nameAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_view,parent,false);
        return new nameAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull nameAdapter.Viewholder holder, int position) {
        final String name = data.get(position);
        int pos = position;

        holder.d_name.setText(pos);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView d_name;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            d_name = itemView.findViewById(R.id.districtname);

        }
    }
}
