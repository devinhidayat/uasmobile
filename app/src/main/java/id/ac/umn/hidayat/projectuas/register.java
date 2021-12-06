package id.ac.umn.hidayat.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

//    private EditText nama;
    private EditText email;
    private EditText password;
    private String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private Button register;
    private FirebaseAuth auth;
    TextView register_text;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                database  = FirebaseDatabase.getInstance();
                myRef = database.getReference("User");

                String regEmail = email.getText().toString();
                String regPassword = password.getText().toString();

                String currentuser = user;

                User helperClass = new User (regPassword, regEmail, currentuser);

                myRef.child(currentuser).setValue(helperClass);
            }
        });
    }

    private void validation(){
        String txt_email = email.getText().toString();
        String txt_password = password.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if(txt_email.isEmpty() && !txt_email.trim().matches(emailPattern)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT);
            toast.show();
        } else if(txt_password.length() < 6 && txt_password.isEmpty()) {
            Toast.makeText(register.this, "Password too short", Toast.LENGTH_SHORT).show();
        } else {
//            FirebaseDatabase.getInstance().getReference().child("Users").push().child("Email").setValue(txt_email);

            registerUser(txt_email, txt_password);
        }
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(register.this, "Registering user successful", Toast.LENGTH_SHORT).show();
                    Intent user = new Intent(register.this, login.class);
                    startActivity(user);
                } else {
                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        register_text = findViewById(R.id.login_user);
        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent (register.this, login.class);
                startActivity(regis);
            }
        });
    }
}