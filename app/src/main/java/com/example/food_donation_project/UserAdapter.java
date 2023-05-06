package com.example.food_donation_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    Context context;
    List<Usermodel> usermodelList;

    public UserAdapter(Context context, List<Usermodel> usermodelList) {
        this.context = context;
        this.usermodelList = usermodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.userdata,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(usermodelList.get(position).getUsername());
        holder.email.setText(usermodelList.get(position).getUseremail());
        holder.address.setText(usermodelList.get(position).getUseraddress());
    }

    @Override
    public int getItemCount() {
        return usermodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.username);
            email=itemView.findViewById(R.id.useremail);
            address=itemView.findViewById(R.id.useraddress);
        }
    }
}
