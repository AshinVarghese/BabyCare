package com.example.baby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout name , userName, userPhoneNumber, userEmail, userPassword;
    Button regButton , loginButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etName);
        userName = findViewById(R.id.etUserName);
        userEmail =  findViewById(R.id.etUserEmail);
        userPhoneNumber = findViewById(R.id.etPhoneNumber);
        userPassword = findViewById(R.id.etUserPassword);

        regButton = findViewById(R.id.btReg);
        loginButton =  findViewById(R.id.btLogin);

        //firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");
                myRef.setValue("Hello, World!");

                //getting values
                String Name = name.getEditText().getText().toString();
                String UserName = userName.getEditText().getText().toString();
                String email = userEmail.getEditText().getText().toString();
                String phone = userPhoneNumber.getEditText().getText().toString();
                String password = userPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(Name,UserName,email,phone,password);

               reference.child(phone).setValue(helperClass);
               //reference.setValue("first data storage");
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }


}