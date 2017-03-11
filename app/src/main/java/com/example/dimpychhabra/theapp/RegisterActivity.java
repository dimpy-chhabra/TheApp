package com.example.dimpychhabra.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

/**
 * Created by Dimpy Chhabra on 3/11/2017.
 */


public class RegisterActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextNumber, editTextPassword;
    Button b1, b2;
    DatabaseHelper myDb;

    private String name;
    private String email;
    private String number;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDb = new DatabaseHelper(this);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextNumber = (EditText)findViewById(R.id.editTextNumber);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        addData();
        //Intent intent = getIntent();

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void addData() {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    name = editTextName.getText().toString();
                    number = editTextNumber.getText().toString();
                    email = editTextEmail.getText().toString();
                    password = editTextPassword.getText().toString();

                    boolean isInserted=myDb.insertData(name, number, email, password);
                    if (isInserted==true)
                        Toast.makeText(RegisterActivity.this,"Data ADDED!",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(RegisterActivity.this,"Not ADDED!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}