package com.example.food_donation_project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.food_donation_project.Donate;
import com.example.food_donation_project.Login_Page;
import com.example.food_donation_project.MainActivity;
import com.example.food_donation_project.My_Cart;
import com.example.food_donation_project.R;
import com.example.food_donation_project.Search_Food;
import com.example.food_donation_project.Update;
import com.example.food_donation_project.databinding.FragmentHomeBinding;

import org.xmlpull.v1.XmlPullParser;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CardView searchfood,donatefood,updatefood,mycart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
searchfood=root.findViewById(R.id.search_food);
donatefood=root.findViewById(R.id.donate_food);
updatefood=root.findViewById(R.id.update_food);
mycart=root.findViewById(R.id.my_cart);




searchfood.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), Search_Food.class);
        startActivity(intent);
    }
});
donatefood.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), Donate.class);
        startActivity(intent);
    }
});
updatefood.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), Update.class);
        startActivity(intent);
    }
});
mycart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), My_Cart.class);
        startActivity(intent);
    }
});


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}