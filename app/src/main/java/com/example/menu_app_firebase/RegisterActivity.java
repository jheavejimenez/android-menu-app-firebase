package com.example.menu_app_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
   FirebaseAuth mAuth;
   EditText inputEmailRegister;
   EditText inputPasswordRegister;
   EditText inputConfirmPassword;
   Button btnRegister;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(R.layout.activity_register);
      mAuth = FirebaseAuth.getInstance();

      inputEmailRegister = findViewById(R.id.inputEmailRegister);
      inputPasswordRegister = findViewById(R.id.inputPasswordRegister);
      inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
      btnRegister = findViewById(R.id.btnRegister);

      btnRegister.setOnClickListener(v -> registerUser());

   }
   private void registerUser() {
      String email = inputEmailRegister.getText().toString().trim();
      String password = inputPasswordRegister.getText().toString().trim();
      String confirmPassword = inputConfirmPassword.getText().toString().trim();

      if (email.isEmpty()) {
         inputEmailRegister.setError("Email is required");
         inputEmailRegister.requestFocus();
         return;
      }

      if (!isEmailValid(email)) {
         inputEmailRegister.setError("Email is not valid");
         inputEmailRegister.requestFocus();
         return;
      }

      if (password.isEmpty()) {
         inputPasswordRegister.setError("Password is required");
         inputPasswordRegister.requestFocus();
         return;
      }

      if (!password.equals(confirmPassword)) {
         inputConfirmPassword.setError("Password does not match");
         inputConfirmPassword.requestFocus();
      }

      if (!isPasswordValid(password)) {
         inputPasswordRegister.setError("Minimum length of password should be 8");
         inputPasswordRegister.requestFocus();
         return;
      }

      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
         if (task.isSuccessful()) {
            finish();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
         } else {
            Toast.makeText(RegisterActivity.this, "Registration Failed" , Toast.LENGTH_SHORT).show();
         }
      });
   }

   private boolean isEmailValid(String email) {
      return email.contains("@");
   }

   private boolean isPasswordValid(String password) {
      return password.length() > 7;
   }
}
