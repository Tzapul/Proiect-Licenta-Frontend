package com.example.tzapt.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tzapt.helpers.Util;
import com.example.tzapt.tasks.GuestTask;
import com.example.tzapt.tasks.LoginTask;

import java.io.IOException;

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
                try {
                    login(emailText.getText().toString(), passwordText.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    private void login(String username, String password) throws IOException {
        if(username.equals(Util.getProperty("admin_username", getApplicationContext())) && password.equals(Util.getProperty("admin_password", getApplicationContext()))) {
            Intent intent = new Intent(this, AdminMainView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }

        LoginTask authService = new LoginTask(this);
        authService.execute(username, password);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Login!", Toast.LENGTH_LONG).show();
    }

}

