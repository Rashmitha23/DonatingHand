package com.example.food_donation_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Details_Activity extends AppCompatActivity {
    TextView quantity;
    int totalQuantity =1;
    int totalprice = 0;
    TextView total;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    DatabaseReference reference;
    ImageView imageView;
    TextView name,rate,description;
    Product_Model product_model = null;
    Button addcard,add,remove;
    String userName = "";
    Usermodel user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();




        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Product_Model){
            product_model = (Product_Model) object;
        }
        imageView=findViewById(R.id.detailimage);
        name=findViewById(R.id.dname);
        description=findViewById(R.id.ddes);
        addcard=findViewById(R.id.addtocard);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);
        quantity=findViewById(R.id.quantity);
        total=findViewById(R.id.totalquantity);

        if (product_model != null){
            Glide.with(getApplicationContext()).load(product_model.getUri()).into(imageView);
            name.setText(product_model.getName());

            description.setText(product_model.getDescription());

        }

        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (auth.getCurrentUser() != null) {


                    String saveCurrentDate, saveCurrenttime;
                    Calendar calForDate = Calendar.getInstance();


                    SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
                    saveCurrentDate = currentDate.format(calForDate.getTime());

                    SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");

                    String productname = name.getText().toString();
                    saveCurrenttime = currenttime.format(calForDate.getTime());

                    reference.child("User").child(auth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                user = task.getResult().getValue(Usermodel.class);
                                final HashMap<String, Object> cartMap = new HashMap<>();
                                cartMap.put("Name", productname);
                                cartMap.put("Price", totalprice);
                                cartMap.put("Date", saveCurrentDate);
                                cartMap.put("Time", saveCurrenttime);
                                cartMap.put("Quantity", totalQuantity);
                              //  cartMap.put("username", user.getUsername());
                              /*  cartMap.put("mobilenumber",user.getUsernumber());
                                cartMap.put("pincode",user.getUserpincode());
                                cartMap.put("locality",user.getUserlocality());
                                cartMap.put("City",user.getUsercity());
                                cartMap.put("state",user.getUserstate());
                                cartMap.put("landmark",user.getUserlandmark());
                                cartMap.put("ulternative",user.getUseralternativenumber());*/
                                //cartMap.put("email", user.getUseremail());
                                firebaseFirestore.collection("AddtoCard").document(auth.getUid())
                                        .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                Toast.makeText(Details_Activity.this, "Add To Cart", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }

                                        });

                            }
                        }
                    });


                }

//                }else {
//                    Intent intent = new Intent(getApplicationContext(), Login_Page.class);
//                    startActivity(intent);
//                    finish();
//                }
            }



        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                }
            }
        });



    }
}