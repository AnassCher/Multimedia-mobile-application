package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Create extends AppCompatActivity {
    final static List<user> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText email = findViewById(R.id.email);
        EditText name = findViewById(R.id.fullname);
        EditText password = findViewById(R.id.password);
        EditText pasconf = findViewById(R.id.confirmpass);

        Button create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String n = name.getText().toString();
                String p = password.getText().toString();
                String c = pasconf.getText().toString();
                if(e.isEmpty() || n.isEmpty() || p.isEmpty() || c.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all the attributs", Toast.LENGTH_SHORT).show();
                }else{
                    if(p.equals(c)){
                        user x = new user(n,e,p);
                        list.add(x);
                        startActivity(new Intent(getApplicationContext(), login.class));
                        Toast.makeText(Create.this, "Create Account Successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}