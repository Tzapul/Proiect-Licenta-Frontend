package com.example.tzapt.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tzapt.tasks.GuestTask;
import com.example.tzapt.tasks.LoginTask;

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

        createAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("Client", "asd");
                LoginActivity.this.startActivity(intent);
            }
        });

        guestAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncTask task = new GuestTask(LoginActivity.this);
                task.execute();
            }
        });
    }

    private void login(String username, String password) {
        Log.i("as", "login");
        LoginTask authService = new LoginTask(this);
        authService.execute(username, password);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Login!", Toast.LENGTH_LONG).show();
    }

}

