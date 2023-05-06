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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.ViewHolder>{
    Context context;
    List<Product_Model> product_modelList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    public UpdateAdapter(Context context, List<Product_Model> product_modelList) {
        this.context = context;
        this.product_modelList = product_modelList;
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UpdateAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.updatedata,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Glide.with(context).load(product_modelList.get(position).getUri()).into(holder.imageView);
        holder.name.setText(product_modelList.get(position).getName());
        holder.description.setText(product_modelList.get(position).getDescription());
        holder.address.setText(product_modelList.get(position).getAddress());
        holder.type.setText(product_modelList.get(position).getType());
        holder.pincode.setText(String.valueOf(product_modelList.get(position).getPin_Code()));
        holder.area.setText(String.valueOf(product_modelList.get(position).getArea()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("Product").document(auth.getCurrentUser().getUid()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context,Update.class);
                                context.startActivity(intent);
                            }
                        });
               /* firebaseFirestore.collection("Product").whereEqualTo("delete","0")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                WriteBatch batch = FirebaseFirestore.getInstance().batch();
                                List<DocumentSnapshot> doc = queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot snapshot:doc){
                                    batch.delete(snapshot.getReference());
                                }
                                batch.commit()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(context, "Delete ", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,description,address,pincode,area,type;
        ImageView imageView,delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
            address=itemView.findViewById(R.id.address);
            pincode=itemView.findViewById(R.id.pincode);
            area=itemView.findViewById(R.id.area_);
            type=itemView.findViewById(R.id.type);
            imageView=itemView.findViewById(R.id.updateimage);
            delete=itemView.findViewById(R.id.deleteproduct);


        }
    }
}
