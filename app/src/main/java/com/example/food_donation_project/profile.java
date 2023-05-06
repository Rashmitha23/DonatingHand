package com.example.food_donation_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profile extends Fragment {
    ImageView profile;
    TextView name,email,number,address;
    Button update;
    FirebaseStorage storage;
    FirebaseUser auth;
    FirebaseDatabase database;
    private String userID;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_profile, container, false);

        storage= FirebaseStorage.getInstance();
        auth= FirebaseAuth.getInstance().getCurrentUser();
        database= FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference("Usersregister");
        userID=firebaseUser.getUid();

        profile=root.findViewById(R.id.profile_image);
        update=root.findViewById(R.id.updatebtn);
        name=root.findViewById(R.id.yourname);
        email=root.findViewById(R.id.youremail);
       // number=root.findViewById(R.id.yourmobilenumber);
        address=root.findViewById(R.id.youraddress);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usermodel userProfile = snapshot.getValue(Usermodel.class);


                if (userProfile != null){
                    String profilename = userProfile.getUsername();
                    String profileemail = userProfile.getUseremail();
                  // String profileumber = userProfile.getUseralternativenumber();
                    String profileaddress = userProfile.getUseraddress();


                    name.setText(profilename);
                    email.setText(profileemail);
                 //  number.setText(profileumber);
                    address.setText(profileaddress);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something worng happened|", Toast.LENGTH_SHORT).show();
            }
        });







        if (auth != null){
            database.getReference().child("User").child(auth.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Usermodel user = snapshot.getValue(Usermodel.class);
                           // Glide.with(getContext()).load(user.getProfileImg()).into(profile);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else {
            Toast.makeText(getContext(), "gg", Toast.LENGTH_SHORT).show();
        }if (auth == null){
            Intent intent = new Intent(getContext(), Login_Page.class);
            startActivity(intent);
            getActivity().finish();
        }



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();

            }
        });

        return root;
    }

    private void updateUserProfile() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData()!= null){
            Uri profileUri = data.getData();
            profile.setImageURI(profileUri);
            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Sucess", Toast.LENGTH_SHORT).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });


        }else {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }





    }
}