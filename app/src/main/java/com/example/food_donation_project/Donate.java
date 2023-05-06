package com.example.food_donation_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Donate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    String[] page = {"Choose Option","Cook","Row","Others"};
    TextView textView;


    ///
    ImageView uploadPicIV;
    EditText name,description,fulladdress,pincode,area;
    private ProgressBar uploadProgressBar;
    final int IMAGE_REQUEST = 71;
    Uri imageLocationPath;
    String id;
    StorageReference objectStorageReference;
    FirebaseFirestore objectFirebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        spinner=findViewById(R.id.spineer);
        description=findViewById(R.id.adddes);
        fulladdress=findViewById(R.id.fulladdress);
        pincode=findViewById(R.id.pincode);
        area=findViewById(R.id.area);
        textView=findViewById(R.id.text);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,page);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        ///
        //  uploadPicET = findViewById(R.id.imageNameET);
        uploadPicIV = findViewById(R.id.imageID);
        name=findViewById(R.id.imageName);
        uploadProgressBar = findViewById(R.id.progress_bar);

        objectStorageReference = FirebaseStorage.getInstance().getReference("imageFolder"); // Create folder to Firebase Storage
        objectFirebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void selectImage(View view){
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,IMAGE_REQUEST);

        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == IMAGE_REQUEST
                    && resultCode == RESULT_OK
                    && data != null
                    && data.getData() != null){
                imageLocationPath = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageLocationPath);
                uploadPicIV.setImageBitmap(objectBitmap);
            }

        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImage(View view){
        try {
            if(/*!uploadPicET.getText().toString().isEmpty() && */imageLocationPath != null){
                //String nameOfimage = "." + getExtension(imageLocationPath);
                String nameOfimage = name.getText().toString() + "." + getExtension(imageLocationPath);
                uploadProgressBar.setVisibility(View.VISIBLE);
                uploadProgressBar.setIndeterminate(true);
                final StorageReference imageRef = objectStorageReference.child(nameOfimage);

                UploadTask objectUploadTask = imageRef.putFile(imageLocationPath);

                objectUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){

                            throw task.getException();

                        }
                        return imageRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            String typename = textView.getText().toString();
                            String nae = name.getText().toString();
                            String des = description.getText().toString();
                            String addrss = fulladdress.getText().toString();
                            String pin = pincode.getText().toString();
                            String ar = area.getText().toString();

                            Map<String,Object> objectMap = new HashMap<>();
                            String docId = objectFirebaseFirestore.collection("data").document().getId();
                            objectMap.put("uri", task.getResult().toString());
                            objectMap.put("name", nae);
                            objectMap.put("description",des);
                            objectMap.put("Address",addrss);
                            objectMap.put("Pin_Code",pin);
                            objectMap.put("Area",ar);
                            objectMap.put("type",typename);
                            objectMap.put("docId",firebaseAuth.getUid());
                            objectFirebaseFirestore.collection("Product").document(firebaseAuth.getCurrentUser().getUid())
                                    .set(objectMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            uploadProgressBar.setVisibility(View.VISIBLE);
                                            uploadProgressBar.setIndeterminate(false);
                                            uploadProgressBar.setProgress(0);
                                            Toast.makeText(Donate.this, "Upload Sucessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        } else if (!task.isSuccessful()){
                            Toast.makeText(Donate.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            } else {
                Toast.makeText(this, "Please provide name for image", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getExtension(Uri uri){
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));

        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = spinner.getSelectedItem().toString();
        textView.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }
}