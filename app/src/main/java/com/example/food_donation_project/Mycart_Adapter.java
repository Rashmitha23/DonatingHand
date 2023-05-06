package com.example.food_donation_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Mycart_Adapter extends RecyclerView.Adapter<Mycart_Adapter.ViewHolder>{
    Context context;
    List<MyCart_Model> myCart_modelList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
int totalPrice =0;

    public Mycart_Adapter(Context context, List<MyCart_Model> myCart_modelList) {
        this.context = context;
        this.myCart_modelList = myCart_modelList;
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycartitem,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myCart_modelList.get(position).getName());
       // holder.price.setText(String.valueOf(myCart_modelList.get(position).getPrice()));
        holder.price.setText(String.valueOf(myCart_modelList.get(position).getPrice()+"₹"));
        holder.quntity.setText(String.valueOf(myCart_modelList.get(position).getQuantity()));
totalPrice = totalPrice+myCart_modelList.get(position).getPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalPrice);
       LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    holder.deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("AddtoCard").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(myCart_modelList.get(position).getDocumentid())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    myCart_modelList.remove(myCart_modelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Delete", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return myCart_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,quntity;
        ImageView deleteitem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.productprice);
            deleteitem=itemView.findViewById(R.id.delete);
            quntity=itemView.findViewById(R.id.totalq);
        }
    }
}
