package com.example.food_donation_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder>{
    Context context;
    List<MyOrderModel> myOrderModelList;

    public MyOrderAdapter(Context context, List<MyOrderModel> myOrderModelList) {
        this.context = context;
        this.myOrderModelList = myOrderModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myOrderModelList.get(position).getName());
        // holder.price.setText(String.valueOf(myCart_modelList.get(position).getPrice()));
        holder.price.setText(String.valueOf(myOrderModelList.get(position).getPrice()+"₹"));
        holder.quntity.setText(String.valueOf(myOrderModelList.get(position).getQuantity()));
        holder.time.setText(myOrderModelList.get(position).getTime());
        holder.date.setText(myOrderModelList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return myOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date,price,quntity,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.order_name);
            date=itemView.findViewById(R.id.order_date);
            price=itemView.findViewById(R.id.order_price);
            quntity=itemView.findViewById(R.id.order_quantity);
            time=itemView.findViewById(R.id.order_time);

        }
    }
}
