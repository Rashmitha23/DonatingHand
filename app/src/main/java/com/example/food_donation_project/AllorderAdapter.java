package com.example.food_donation_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AllorderAdapter extends RecyclerView.Adapter<AllorderAdapter.ViewHolder>{
    Context context;
    List<MyOrderModel> myOrderModelList;

    public AllorderAdapter(Context context, List<MyOrderModel> myOrderModelList) {
        this.context = context;
        this.myOrderModelList = myOrderModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllorderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.allorder,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myOrderModelList.get(position).getName());
        // holder.price.setText(String.valueOf(myCart_modelList.get(position).getPrice()));
        holder.date.setText(myOrderModelList.get(position).getDate());
      //  holder.quantity.setText(myOrderModelList.get(position).getQuantity());
        holder.time.setText(myOrderModelList.get(position).getTime());

        holder.quantity.setText(String.valueOf(myOrderModelList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return myOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date,price,quantity,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.allordername);
            date=itemView.findViewById(R.id.allorderdate);
            quantity=itemView.findViewById(R.id.allquntity);
            time=itemView.findViewById(R.id.allordertime);
        }
    }
}
