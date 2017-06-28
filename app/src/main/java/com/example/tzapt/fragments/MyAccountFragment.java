package com.example.tzapt.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tzapt.activities.R;
import com.example.tzapt.models.Account;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.User;
import com.example.tzapt.tasks.UpdateUserTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    private View view;
    private User client;

    private TextView firstnameText;
    private TextView lastnameText;
    private TextView emailText;
    private TextView phoneText;
    private TextView passwordText;

    private EditText firstnameEdit;
    private EditText lastnameEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText passwordEdit;

    private Button saveChangesBtn;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_my_account, container, false);


        firstnameText = (TextView) view.findViewById(R.id.fNameLbl);
        lastnameText = (TextView) view.findViewById(R.id.lNameLbl);
        emailText = (TextView) view.findViewById(R.id.phoneLbl);
        phoneText = (TextView) view.findViewById(R.id.emailLbl);
        passwordText = (TextView) view.findViewById(R.id.passwordLbl);

        firstnameEdit = (EditText) view.findViewById(R.id.fNameText);
        lastnameEdit = (EditText) view.findViewById(R.id.lNameText);
        emailEdit = (EditText) view.findViewById(R.id.emailText);
        phoneEdit = (EditText) view.findViewById(R.id.phoneText);
        passwordEdit = (EditText) view.findViewById(R.id.passwordText);

        saveChangesBtn = (Button) view.findViewById(R.id.saveBtn);

        client = (User) getArguments().getSerializable("client");

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateUser(firstnameEdit.getText().toString(), lastnameEdit.getText().toString(),
                        emailEdit.getText().toString(), phoneEdit.getText().toString(), passwordEdit.getText().toString());
            }
        });

        populateFields();
        return view;
    }

    private void updateUser(String firstName, String lastName, String email, String phone, String password) {
        AsyncTask updateUser = new UpdateUserTask((AppCompatActivity)getActivity(), this.client);
        updateUser.execute(firstName, lastName, email, phone, password, client.getId());
    }

    private void populateFields() {

        Account account = client.getAccount();
        PersonDetails details = client.getPersonDetails();

        firstnameEdit.setText(details.getFirstName());
        lastnameEdit.setText(details.getLastName());
        phoneEdit.setText(details.getPhone());
        emailEdit.setText(account.getEmail());
    }

}
