package com.example.it_vyzovcitek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.it_vyzovcitek.bottomnav.quiz.QuizFragment;
import com.example.it_vyzovcitek.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    public FirebaseDatabase database;
    public DatabaseReference reference;


    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        binding.minibackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        setContentView(binding.getRoot());

        binding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEt.getText().toString().isEmpty() || binding.loginEt.getText().toString().isEmpty()
                        || binding.passwordEt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEt.getText().toString(), binding.passwordEt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
                                        userRef.child("username").setValue(binding.loginEt.getText().toString());
                                        userRef.child("profileImage").setValue("");
                                        userRef.child("email").setValue(binding.emailEt.getText().toString());
                                        userRef.child("password").setValue(binding.passwordEt.getText().toString());
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "You have some errors", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }
}