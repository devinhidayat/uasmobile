package id.ac.umn.hidayat.projectuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;


public class register extends AppCompatActivity {

//    private EditText nama;
    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;
    TextView register_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();


        Button showpassword = findViewById(R.id.showPass);
        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });
    }

    private void validation(){
        String txt_email = email.getText().toString();
        String txt_password = password.getText().toString();

        if(txt_email.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT);
            toast.show();
        } else if(txt_password.length() < 6 && txt_password.isEmpty()) {
            Toast.makeText(register.this, "Password too short", Toast.LENGTH_SHORT).show();
        } else {
            registerUser(txt_email, txt_password);
        }
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(register.this, "Registering user successful", Toast.LENGTH_SHORT).show();
                    Intent user = new Intent(register.this, MainActivity.class);
                    startActivity(user);
                } else {
                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        register_text = findViewById(R.id.log);
        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent (register.this, login.class);
                startActivity(regis);
            }
        });
    }
}