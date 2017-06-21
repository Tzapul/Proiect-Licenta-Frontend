package com.example.tzapt.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import android.util.Log;
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

import com.example.tzapt.tasks.LoginTask;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.R.attr.button;
import static com.example.tzapt.activities.R.id.login;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText passwordText;
    private EditText emailText;
    private TextView createAccount;
    private TextView guestAccount;

    private Button signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        passwordText = (EditText) findViewById(R.id.passwordText);
        emailText = (EditText) findViewById(R.id.emailText);
        createAccount = (TextView) findViewById(R.id.createAccount);
        guestAccount = (TextView)findViewById(R.id.guestAccount);

        signInButton = (Button) findViewById(R.id.signInBtn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login(emailText.getText().toString(), passwordText.getText().toString());
            }
        });
    }

    private void login(String username, String password) {
        Log.i("as", "login");
        LoginTask authService = new LoginTask(username, password);
        authService.execute();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Login!", Toast.LENGTH_LONG).show();
    }

}

