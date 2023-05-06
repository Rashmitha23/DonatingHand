package com.example.food_donation_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


public class Logout extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

   FirebaseAuth firebaseAuth;


        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signOut();
        startActivity(new Intent(getContext(), Login_Page.class));
        getActivity().finish();



        return root;
    }
}