package com.example.newsapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import org.w3c.dom.Text;

public class accountProfileActivity extends AppCompatActivity {

    static  boolean isUserLoggedIn = false;
    static String username = " ", email;
    static private String password = "null";
    EditText userNameView, emailView,currentPasswordView, newPasswordView;
    ImageButton editUsername, editEmail,saveMail, saveUsername, savePassword;
    Button logout;
    LinearLayout passWordLinearLayout;
    TextView changePassword;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        accountProfileActivity.password = password;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAccount);
        toolbar.setTitle("Account");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();

        userNameView = (EditText) findViewById(R.id.namePage);
        emailView = (EditText) findViewById(R.id.mailPage);
        editUsername = (ImageButton) findViewById(R.id.changeUsernameButton);
        editEmail = (ImageButton) findViewById(R.id.changeEmailButton);
        //editPassword = (ImageButton) findViewById(R.id.changePasswordButton);
        saveMail = (ImageButton) findViewById(R.id.saveEmail);
        saveUsername = (ImageButton) findViewById(R.id.saveUserName);
        savePassword = (ImageButton) findViewById(R.id.savePassword);
        currentPasswordView = (EditText) findViewById(R.id.currentPassword);
        newPasswordView = (EditText) findViewById(R.id.newPassword);
        logout = (Button) findViewById(R.id.logout);
        passWordLinearLayout = (LinearLayout) findViewById(R.id.changePasswordLayout);
        changePassword = (TextView) findViewById(R.id.changePassword);

        if(accountProfileActivity.getPassword().equals("null"))
        {
            passWordLinearLayout.setVisibility(View.GONE);
        }


        //Google Signup
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        userNameView.setText(accountProfileActivity.username);
        emailView.setText(accountProfileActivity.email);

        userNameView.setEnabled(false);
        emailView.setEnabled(false);

        saveUsername.setVisibility(View.GONE);
        saveMail.setVisibility(View.GONE);
        savePassword.setVisibility(View.GONE);

        editUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameView.setEnabled(true);
                saveUsername.setVisibility(View.VISIBLE);
                editUsername.setVisibility(View.GONE);
            }
        });

        saveUsername.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String changedUserName = userNameView.getText().toString();
                if (changedUserName.isEmpty() || changedUserName.chars().allMatch(Character::isWhitespace) )
                {
                    userNameView.setError("User name can not be empty");
                }
                else {
                    userNameView.setEnabled(false);
                    accountProfileActivity.username = changedUserName;
                    userNameView.setText(accountProfileActivity.username);
                    saveUsername.setVisibility(View.GONE);
                }
                editUsername.setVisibility(View.VISIBLE);
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailView.setEnabled(true);
                saveMail.setVisibility(View.VISIBLE);
                editEmail.setVisibility(View.GONE);
            }
        });

        saveMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();//loginFragment.getUserLogin();
                accountProfileActivity.email = emailView.getText().toString();
                user.updateEmail(accountProfileActivity.email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Log.d(TAG, "User email address updated.");
                                    emailView.setEnabled(false);
                                    emailView.setText(accountProfileActivity.email);
                                    saveMail.setVisibility(View.GONE);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                                editEmail.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPasswordView.setVisibility(View.VISIBLE);
                newPasswordView.setVisibility(View.VISIBLE);
                savePassword.setVisibility(View.VISIBLE);
                //editPassword.setVisibility(View.GONE);
            }
        });

        savePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword = currentPasswordView.getText().toString();
                String newPassword = newPasswordView.getText().toString();
                newPassword = newPassword.equals("") ? "null" : newPassword;
                final String finalNewPassword = newPassword;
                if(!currentPassword.equals(accountProfileActivity.getPassword()))
                {
                    currentPasswordView.setError("Current Password is Wrong!");
                }
                else
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                       // Log.d(TAG, "User password updated.");
                                        currentPasswordView.setVisibility(View.GONE);
                                        newPasswordView.setVisibility(View.GONE);
                                        savePassword.setVisibility(View.GONE);
                                        if(!finalNewPassword.equals("null"))
                                        accountProfileActivity.setPassword(finalNewPassword);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                    //editPassword.setVisibility(View.VISIBLE);
                                }
                            });
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                accountProfileActivity.isUserLoggedIn = false;
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    int RC_SIGN_IN = 65;
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
                            //Log.d(TAG, "signInWithCredential:success");
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
                            //updateUI(null);
                        }
                    }
                });
    }
}