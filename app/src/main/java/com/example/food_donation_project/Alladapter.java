package com.example.food_donation_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Alladapter extends RecyclerView.Adapter<Alladapter.ViewHolder>{
    Context context;
    List<Product_Model> product_modelList;

    public Alladapter(Context context, List<Product_Model> product_modelList) {
        this.context = context;
        this.product_modelList = product_modelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Alladapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.allfood,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(product_modelList.get(position).getUri()).into(holder.imageView);
        holder.name.setText(product_modelList.get(position).getName());
        holder.description.setText(product_modelList.get(position).getDescription());
        holder.address.setText(product_modelList.get(position).getAddress());
        holder.pincode.setText(product_modelList.get(position).getPin_Code());

    }

    @Override
    public int getItemCount() {
        return product_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,address,pincode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.allfoodimg);
            name=itemView.findViewById(R.id.allname);
            description=itemView.findViewById(R.id.alldescription);
            address=itemView.findViewById(R.id.allfoodaddress);
            pincode=itemView.findViewById(R.id.allfoodpin);

        }
    }
}
