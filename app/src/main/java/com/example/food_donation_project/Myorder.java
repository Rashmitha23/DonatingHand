package com.example.food_donation_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Myorder extends Fragment {

    RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;
    List<MyOrderModel> myOrderModelList;
    FirebaseFirestore db;
    FirebaseUser auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myorder, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = root.findViewById(R.id.myorderrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        myOrderModelList = new ArrayList<>();
        myOrderAdapter = new MyOrderAdapter(getContext(), myOrderModelList);
        recyclerView.setAdapter(myOrderAdapter);
        if (auth != null) {
            db.collection("CurrentUser").whereEqualTo("uid", auth.getUid())
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
        } else {

        }
        if (auth == null) {
            Intent intent = new Intent(getContext(), Login_Page.class);
            startActivity(intent);
            getActivity().finish();

        }
    return root;



    }
}