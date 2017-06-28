package com.example.tzapt.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tzapt.tasks.RegisterTask;

public class RegisterActivity extends AppCompatActivity {

    private TextView firstnameText;
    private TextView lastnameText;
    private TextView emailText;
    private TextView phoneText;
    private TextView usernameText;
    private TextView passwordText;

    private EditText firstnameEdit;
    private EditText lastnameEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText usernameEdit;
    private EditText passwordEdit;

    private Button cancelButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstnameText = (TextView) findViewById(R.id.fNameLbl);
        lastnameText = (TextView) findViewById(R.id.lNameLbl);
        emailText = (TextView) findViewById(R.id.phoneLbl);
        phoneText = (TextView) findViewById(R.id.emailLbl);
        usernameText = (TextView) findViewById(R.id.usernameTextView);
        passwordText = (TextView) findViewById(R.id.passwordLbl);

        firstnameEdit = (EditText) findViewById(R.id.fNameText);
        lastnameEdit = (EditText) findViewById(R.id.lNameText);
        emailEdit = (EditText) findViewById(R.id.emailText);
        phoneEdit = (EditText) findViewById(R.id.phoneText);
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);

        cancelButton = (Button) findViewById(R.id.cancelBtn);
        registerButton = (Button) findViewById(R.id.registerBtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(firstnameEdit.getText().toString(), lastnameEdit.getText().toString(),
                        emailEdit.getText().toString(), phoneEdit.getText().toString(),
                        usernameEdit.getText().toString(), passwordEdit.getText().toString());
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

    }

    private void register(String firstName, String lastName, String email, String phone, String username, String password) {
        AsyncTask registerTask = new RegisterTask(this);
        registerTask.execute(firstName, lastName,email, phone, username, password);
    }

}
