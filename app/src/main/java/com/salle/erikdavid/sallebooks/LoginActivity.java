package com.salle.erikdavid.sallebooks;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.salle.erikdavid.sallebooks.Utils.KeyConstants;
import com.salle.erikdavid.sallebooks.Utils.LoginUtils;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView mToRegisterView;
    Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide Action bar
        ActionBar bar;
        if((bar = getSupportActionBar()) != null)
            bar.hide();

        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mToRegisterView = findViewById(R.id.login_to_register_link);
        mEmailSignInButton = findViewById(R.id.sign_in_button);
        mPasswordView = findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mEmailSignInButton.setOnClickListener(this);
        mToRegisterView.setOnClickListener(this);
    }

    /**
     * Initialize an attempt to enter to application services.
     */
    private void attemptLogin() {

        boolean loginSuccessfull = false;
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String savedEmail = sharedPref.getString(KeyConstants.EMAIL, null);
        String savedPassword = sharedPref.getString(KeyConstants.PASSWORD, null);

        if(savedEmail != null && savedPassword != null){
            if(savedEmail.equals(email) && savedPassword.equals(password)){
                loginSuccessfull = true;
            } else {
                Toast.makeText(this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }

        if (loginSuccessfull){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.sign_in_button:
                attemptLogin();
                break;
            case R.id.login_to_register_link:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(i, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(getApplicationContext(), getString(R.string.account_ok), Toast.LENGTH_SHORT).show();
                String email = data.getStringExtra(KeyConstants.EMAIL);
                mEmailView.setText(email);
            }

        }
    }
}

