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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register_Page extends AppCompatActivity {
    TextView gotolog;
    EditText name,email,address,pasword;
    Button btn;
    Activity mContext = this;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        gotolog=findViewById(R.id.gotologin);
        name=findViewById(R.id.regname);
        email=findViewById(R.id.regemail);
        address=findViewById(R.id.regaddress);
        pasword=findViewById(R.id.regpassword);
        btn=findViewById(R.id.registerdbtn);

        fAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        fstore=FirebaseFirestore.getInstance();

        gotolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Page.this,Login_Page.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = name.getText().toString();
                String useremail = email.getText().toString();
                String useraddress = address.getText().toString();
                String userpassword = pasword.getText().toString();

                if (username.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setMessage("Name cannot be empty");
                    alert.setCancelable(false);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();
                }
                else if(useremail.isEmpty()){
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
                else if(useraddress.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setMessage("Address cannot be empty");
                    alert.setCancelable(false);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }
                else if(userpassword.length() < 8){
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setMessage("Password length must not be less than 8 Char");
                    alert.setCancelable(false);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }else {

                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),pasword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String uid = task.getResult().getUser().getUid();
                                Usermodel users = new Usermodel (name.getText().toString(),email.getText().toString(),address.getText().toString(),pasword.getText().toString(),uid,0);

                                database.getReference().child("Usersregister").child(uid).setValue(users);
                                Toast.makeText(Register_Page.this, "User Create", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register_Page.this, Login_Page.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });



                }



            }
        });

    }
}