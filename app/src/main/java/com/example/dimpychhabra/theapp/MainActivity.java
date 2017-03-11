package com.example.dimpychhabra.theapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    EditText editTextEmail, editTextPassword;
    DatabaseHelper myDb;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        checkdata();


        //Intent intent = getIntent();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void checkdata() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                Log.e("in main Activity >>" + email + "<<", " and password is >>" + password + "<<");


                boolean isInserted = myDb.checkData(email, password);
                if (isInserted == true){
                    Toast.makeText(MainActivity.this, "Welcome Aboard!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(MainActivity.this, "Bro! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}