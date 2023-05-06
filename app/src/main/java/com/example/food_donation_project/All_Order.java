package com.example.food_donation_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class All_Order extends AppCompatActivity {
    RecyclerView recyclerView;
    AllorderAdapter myOrderAdapter;
    List<MyOrderModel> myOrderModelList;
    FirebaseFirestore db;
    FirebaseUser auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView =findViewById(R.id.orderrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        myOrderModelList = new ArrayList<>();
        myOrderAdapter = new AllorderAdapter(getApplicationContext(), myOrderModelList);
        recyclerView.setAdapter(myOrderAdapter);

            db.collection("CurrentUser")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    String documentid = documentSnapshot.getId();
                                    MyOrderModel myOrderModel = documentSnapshot.toObject(MyOrderModel.class);
                                    //myOrderModel.setDocumentid(documentid);
                                    myOrderModelList.add(myOrderModel);
                                    myOrderAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });



    }
}