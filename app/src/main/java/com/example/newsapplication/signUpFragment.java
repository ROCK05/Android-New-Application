package com.example.newsapplication;

//import android.databinding.tool.util.StringUtils;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signUpFragment extends Fragment {

    EditText userNameText,emailText,passwordText;
    //String email, password,userName;
    Button signupButton, login, signup;
    TextView alreadyHaveAccount;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mAuth = FirebaseAuth.getInstance();
        signupButton = (Button) view.findViewById(R.id.createNewAccount);
        userNameText = (EditText) view.findViewById(R.id.usernameSignup);
        emailText = (EditText) view.findViewById(R.id.emailSignup);
        passwordText = (EditText) view.findViewById(R.id.passwordSignup);
        alreadyHaveAccount = (TextView) view.findViewById(R.id.alreadyAccount);
        login = (Button) view.findViewById(R.id.logInButton);
        signup = (Button) view.findViewById(R.id.signUpButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String userName = userNameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                email = email.equals("") ? "null" : email;
               password = password.equals("") ? "null" : password;
                final String  finalEmail = email;
                final String finalPassword = password;
                if (userName.isEmpty() || userName.chars().allMatch(Character::isWhitespace) )
                {
                    userNameText.setError("User name can not be empty");
                    getActivity().recreate();
                }
                else {
                    final String finalUserName = userName;
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Account created successfully", Toast.LENGTH_SHORT).show();
                                        accountProfileActivity.isUserLoggedIn = true;
                                        accountProfileActivity.email = finalEmail;
                                        accountProfileActivity.username = finalUserName;
                                        accountProfileActivity.setPassword(finalPassword);
                                        Intent intent = new Intent(getActivity(),accountProfileActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getActivity(), "Some fields are empty or " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }


                            });
                }
            }
        });
        return view;
    }
}