package com.salle.erikdavid.sallebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.salle.erikdavid.sallebooks.Utils.KeyConstants;
import com.salle.erikdavid.sallebooks.Utils.LoginUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mRegisterButton;
    private EditText mEmailView;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private CheckBox mSeePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set form views
        mRegisterButton = findViewById(R.id.register_button);
        mEmailView = findViewById(R.id.register_email);
        mUsernameView = findViewById(R.id.register_username);
        mPasswordView = findViewById(R.id.register_password);
        mSeePass = findViewById(R.id.register_checkbox);

        mSeePass.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_button:
                attemptCreateAccount();
                break;
            case R.id.register_checkbox:
                if(mSeePass.isChecked()){
                    mPasswordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    mPasswordView.setTransformationMethod(PasswordTransformationMethod.getInstance());;
                }
                break;
        }
    }

    private void attemptCreateAccount() {

        boolean registerSuccessful = true;
        String email = mEmailView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !LoginUtils.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            registerSuccessful = false;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            registerSuccessful = false;
        } else if (!LoginUtils.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            registerSuccessful = false;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            registerSuccessful = false;
        }

        if (registerSuccessful){
            createAccount(email, username, password);
        }
    }

    private void createAccount(String email, String username, String password) {
        // Writing into database
        SharedPreferences.Editor editor = getSharedPreferences(KeyConstants.MINIDATABASE_NAME, MODE_PRIVATE).edit();
        editor.putString(KeyConstants.EMAIL, email);
        editor.putString(KeyConstants.USERNAME, username);
        editor.putString(KeyConstants.PASSWORD, password);
        editor.apply();

        // Return to login
        Intent returnIntent = new Intent();
        returnIntent.putExtra(KeyConstants.EMAIL, email);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }
}
