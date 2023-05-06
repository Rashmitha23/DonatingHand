package com.example.food_donation_project;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class All_Users extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<Usermodel> usermodelList;
    FirebaseFirestore db;
    FirebaseUser auth;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView =findViewById(R.id.userrec);
        mbase
                = FirebaseDatabase.getInstance().getReference("Usersregister");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        usermodelList = new ArrayList<>();
        userAdapter = new UserAdapter(getApplicationContext(), usermodelList);
        recyclerView.setAdapter(userAdapter);
// Read from the database

mbase.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
            Usermodel user = dataSnapshot.getValue(Usermodel.class);
            usermodelList.add(user);
        }
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

    }
}