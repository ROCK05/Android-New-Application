package com.example.newsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginActivity1 extends AppCompatActivity {

    Button login,signup;
    ImageButton google;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    TextView dontHaveAccount,alreadyHaveAccount;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.accountToolbar);
        toolbar.setTitle("Account");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        login = (Button) findViewById(R.id.logInButton);
        signup = (Button) findViewById(R.id.signUpButton);
        google = (ImageButton) findViewById(R.id.gooleButton);
        dontHaveAccount = (TextView) findViewById(R.id.dontHaveAccount);
        alreadyHaveAccount = (TextView) findViewById(R.id.alreadyAccount);

        signup.setTextColor(Color.BLUE);
        login.setTextColor(Color.GRAY);

        //Google Signup
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        //Login fragment button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alreadyHaveAccount.setVisibility(View.GONE);
                dontHaveAccount.setVisibility(View.VISIBLE);
                login.setTextColor(Color.BLUE);
                signup.setTextColor(Color.GRAY);
                loginFragment loginFragment = new loginFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginFragmentContainer,loginFragment);
                transaction.commit();

            }
        });

        //Login fragment text
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alreadyHaveAccount.setVisibility(View.GONE);
                dontHaveAccount.setVisibility(View.VISIBLE);
                login.setTextColor(Color.BLUE);
                signup.setTextColor(Color.GRAY);
                loginFragment loginFragment = new loginFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginFragmentContainer,loginFragment);
                transaction.commit();
            }
        });

        //Signup fragment button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dontHaveAccount.setVisibility(View.GONE);
                alreadyHaveAccount.setVisibility(View.VISIBLE);
                signup.setTextColor(Color.BLUE);
                login.setTextColor(Color.GRAY);
                signUpFragment signUpFragment = new signUpFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.loginFragmentContainer,signUpFragment);
                transaction.commit();
            }
        });

        //Signup fragment text
        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dontHaveAccount.setVisibility(View.GONE);
                alreadyHaveAccount.setVisibility(View.VISIBLE);
                signup.setTextColor(Color.BLUE);
                login.setTextColor(Color.GRAY);
                signUpFragment signUpFragment = new signUpFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//.beginTransaction();
                transaction.replace(R.id.loginFragmentContainer,signUpFragment);
                transaction.commit();
            }
        });

        //Google Button
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }

    int RC_SIGN_IN = 65;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public static void signOutGoogle()
    {
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(getApplicationContext(), "Logged In with Google", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            accountProfileActivity.isUserLoggedIn = true;
                            accountProfileActivity.email = user.getEmail();
                            accountProfileActivity.username = user.getDisplayName();
                            Intent intent = new Intent(getApplicationContext(),accountProfileActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

}