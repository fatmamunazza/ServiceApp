package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class EditProfile extends AppCompatActivity {

    TextInputLayout name,email,phone;
    EditText etName,etEmail;
    ImageView submit,close;
    TextView editProfile;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);

        etName=findViewById(R.id.eTname);
        etEmail=findViewById(R.id.eTemail);

        submit=findViewById(R.id.submit);
        close=findViewById(R.id.close);

        progressBar=findViewById(R.id.progressBar);

        editProfile=findViewById(R.id.editProfile);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etName.getText().toString().trim().length()==0){
                    name.setErrorEnabled(true);
                    name.setError("Name cannot be blank");
                }
                else{
                    name.setErrorEnabled(false);

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        final String email_pattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etEmail.getText().toString().trim().length()==0){
                    email.setErrorEnabled(true);
                    email.setError("Email cannot be blank");
                }
                else if(etEmail.getText().toString().matches(email_pattern)!=true){
                    email.setErrorEnabled(true);
                    email.setError("Invalid Email Id");
                }
                else{
                    email.setErrorEnabled(false);

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().trim().length()==0){
                    name.setErrorEnabled(true);
                    name.setError("Name cannot be blank");
                }
                if(etEmail.getText().toString().trim().length()==0){
                    email.setErrorEnabled(true);
                    email.setError("Email cannot be blank");
                }
                else if(etEmail.getText().toString().matches(email_pattern)!=true){
                    email.setErrorEnabled(true);
                    email.setError("Invalid Email Id");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    editProfile.setVisibility(View.GONE);
                    submit.setVisibility(View.INVISIBLE);
                    finish();
                    overridePendingTransition(R.anim.no_animation,R.anim.slide_out_bottom);
                    Toast.makeText(EditProfile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.no_animation,R.anim.slide_out_bottom);
            }
        });
    }
}
