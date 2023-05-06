package com.example.food_donation_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Place_Order extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();



        String docId = UUID.randomUUID().toString();
        List<MyCart_Model> list = (ArrayList<MyCart_Model>) getIntent().getSerializableExtra("itemList");
        if (list != null && list.size()>0){
            for (MyCart_Model model : list){

                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("Name", model.getName());
                cartMap.put("Price", model.getPrice());
                cartMap.put("Date", model.getDate());
                cartMap.put("Time", model.getTime());
                cartMap.put("Quantity", model.getQuantity());
                cartMap.put("username",model.getUsername());
                cartMap.put("mobilenumber",model.getMobilenumber());
                cartMap.put("pincode",model.getPincode());
                cartMap.put("locality",model.getLocality());
                cartMap.put("City",model.getCity());
                cartMap.put("state",model.getState());
                cartMap.put("landmark",model.getLandmark());
                cartMap.put("ulternative",model.getUlternative());
                cartMap.put("email",model.getEmail());
                cartMap.put("uid",auth.getCurrentUser().getUid());
                cartMap.put("docId",docId);


                firebaseFirestore.collection("CurrentUser").document(docId)
                        .set(cartMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Place_Order.this, "Sucess", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        }

    }
}