package com.example.food_donation_project;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Update extends AppCompatActivity {
    RecyclerView productView;
    List<Product_Model> product_modelList;
    UpdateAdapter updateAdapter;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        productView = findViewById(R.id.productrec);
        productView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        product_modelList = new ArrayList<>();
        updateAdapter = new UpdateAdapter(getApplicationContext(), product_modelList);
        productView.setAdapter(updateAdapter);
        auth = getInstance();

    /*    database.collection("Product")
                //.document(auth.getCurrentUser().getUid())

                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult()){
                                Product_Model product_model = documentSnapshot.toObject(Product_Model.class);
                                product_modelList.add(product_model);
                                // product_adapter.notifyDataSetChanged();
                                updateAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });*/

        database.collection("Product")
                //.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()
                )
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                            Product_Model product_model = document.toObject(Product_Model.class);
                            product_modelList.add(product_model);
                            // product_adapter.notifyDataSetChanged();
                            updateAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(Update.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}