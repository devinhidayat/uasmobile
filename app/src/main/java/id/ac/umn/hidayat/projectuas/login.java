package id.ac.umn.hidayat.projectuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private TextView registerUser;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registerUser = findViewById(R.id.register_user);

        mAuth = FirebaseAuth.getInstance();

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this , register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

                if(txt_email.isEmpty() && !txt_email.trim().matches(emailPattern)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT);
                    toast.show();
                } else if(txt_password.length() < 6 && txt_password.isEmpty()) {
                    Toast.makeText(login.this, "Password too short", Toast.LENGTH_SHORT).show();
                } else {
//                    FirebaseDatabase.getInstance().getReference().child("Users").push().child("Email").setValue(txt_email);

                    loginUser(txt_email , txt_password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this , MainActivity.class);
                    String et_email = email;
                    intent.putExtra("email", et_email);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
        });
    }
}