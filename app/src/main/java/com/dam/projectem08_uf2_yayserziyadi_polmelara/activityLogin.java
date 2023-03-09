package com.dam.projectem08_uf2_yayserziyadi_polmelara;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class activityLogin extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btnLogin;
    EditText email, password;
    TextView textViewCreateAccount;
    ImageView imageView;
    int numColor = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        btnLogin = findViewById(R.id.buttonLogin);
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        imageView = findViewById(R.id.imageView);
        mAuth = FirebaseAuth.getInstance();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorChange();
            }
        });

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(activityLogin.this, "Ingresa los datos restantes", Toast.LENGTH_LONG).show();
                }else{
                    loginUser(emailUser,passUser);

                }
            }
        });
    }

    public void openActivityRegister(){
        Intent intent = new Intent(this, activityRegister.class);
        startActivity(intent);
    }

    private void colorChange(){
        if (numColor ==6){
            numColor = 1;
        }
        switch (numColor){
            case 1:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.purple_200));
                break;
            case 2:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.purple_500));
                break;
            case 3:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.purple_700));
                break;
            case 4:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.teal_200));
                break;
            case 5:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.teal_700));
                Toast.makeText(this, "Toast",Toast.LENGTH_SHORT).show();
                break;
        }
        numColor++;
    }

    private void loginUser(String emailUser, String passUser){
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(activityLogin.this,MapsActivity.class));
                    Toast.makeText(activityLogin.this, "Bienvenido", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(activityLogin.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activityLogin.this, "Error al logearse", Toast.LENGTH_LONG).show();
            }
        });
    }

}
