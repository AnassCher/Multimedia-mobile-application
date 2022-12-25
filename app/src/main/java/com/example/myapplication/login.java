package com.example.myapplication;



import static com.example.myapplication.Create.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        EditText email = (EditText) findViewById(R.id.Email);
        EditText pass = (EditText) findViewById(R.id.Password);



        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(email.hasFocus()){
                    email.setText("");
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(pass.hasFocus()){
                    pass.setText("");
                }
            }
        });

        Button connexion = (Button) findViewById(R.id.button);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pas = pass.getText().toString();

                user x = new user("name", em, pas);
                for(user h: list){
                    if(x.getEmail().equals(h.getEmail()) && x.getPassword().equals(h.getPassword())){


                        String s = h.getName();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("name", s);
                        //intent.putExtra("image",)
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Welcome Back "+h.getName(), Toast.LENGTH_SHORT).show();
                    }}
                 if (em.equals("Anass") && pas.equals("123")){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("name","Owner");
                        intent.putExtra("image",R.drawable.me);

                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Welcome Back "+em, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"erreur", Toast.LENGTH_SHORT).show();
                    }


                }



        });
        Button creeCompte = findViewById(R.id.cree);
        creeCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Create.class));
            }
        });

    }
}