package com.example.newsapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class loginFragment extends Fragment {

    EditText emailText,passwordText;

    Button loginButton;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();
        loginButton = (Button) view.findViewById(R.id.login);
        emailText = (EditText) view.findViewById(R.id.emailLogin);
        passwordText = (EditText) view.findViewById(R.id.passwordLogin);

      loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              String email = emailText.getText().toString();
              String password = passwordText.getText().toString();
              email = email.equals("") ? "null" : email;
              password = password.equals("") ? "null" : password;

              final String finalPassword = password;
              mAuth.signInWithEmailAndPassword(email, password)
                      .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()) {

                                  Toast.makeText(getActivity(),"Logged in successfully", Toast.LENGTH_SHORT).show();
                                  FirebaseUser user = mAuth.getCurrentUser();
                                  accountProfileActivity.isUserLoggedIn = true;
                                  accountProfileActivity.email = user.getEmail();
                                  accountProfileActivity.setPassword(finalPassword);
                                  accountProfileActivity.username = user.getDisplayName();
                                  Intent intent = new Intent(getActivity(),accountProfileActivity.class);
                                  startActivity(intent);
                              }
                              else {
                                  Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
          }
      });
        return view;
    }

}