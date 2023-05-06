package com.example.food_donation_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Page extends AppCompatActivity {
    TextView gotoreg;
    EditText email,password;
    Button btn;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    Activity mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        gotoreg=findViewById(R.id.gotoregister);
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpassword);
        btn=findViewById(R.id.loigbtn);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        gotoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Page.this,Register_Page.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();

                if (useremail.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setMessage("Email cannot be empty");
                    alert.setCancelable(false);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();
                }
                else if(userpassword.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setMessage("Password cannot be empty");
                    alert.setCancelable(false);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }else {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String uid = task.getResult().getUser().getUid();
                                firebaseDatabase.getReference().child("Usersregister").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int usertype = snapshot.getValue(Integer.class);
                                        if (usertype == 0){
                                            Intent intent = new Intent(Login_Page.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                        if (usertype == 1){
                                            Intent intent = new Intent(Login_Page.this, Admin_Page.class);
                                            startActivity(intent);
                                            finish();

                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



                            }

                        }
                    });




                }


            }
        });


    }
}