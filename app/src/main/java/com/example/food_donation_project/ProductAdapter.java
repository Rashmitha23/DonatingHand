package com.example.food_donation_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    List<Product_Model> product_modelList;

    public ProductAdapter(Context context, List<Product_Model> product_modelList) {
        this.context = context;
        this.product_modelList = product_modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(product_modelList.get(position).getUri()).into(holder.imageView);
        holder.name.setText(product_modelList.get(position).getName());
        holder.area.setText(String.valueOf(product_modelList.get(position).getArea()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n_act = new Intent(context, Details_Activity.class);
                n_act.putExtra("detail",product_modelList.get(position));
                n_act.putExtra("type",product_modelList.get(position).getType());
                n_act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(n_act);
            }
        });

    }

    @Override
    public int getItemCount() {
        return product_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView area,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.p_image);
            area=itemView.findViewById(R.id.pp);
            name=itemView.findViewById(R.id.pname);


        }
    }
}
