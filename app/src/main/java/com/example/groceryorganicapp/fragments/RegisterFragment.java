package com.example.groceryorganicapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.models.LoginRegiUserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterFragment<gmail> extends Fragment {

    EditText username, gmail, password, repassword;
    Button register;


    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference loginRegiReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        findViewById(v);
        onClickview();
        return v;
    }

    private void findViewById(View v) {
        username = v.findViewById(R.id.username);
        gmail = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        repassword = v.findViewById(R.id.repassword);
        register = v.findViewById(R.id.register);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
        loginRegiReference=firebaseDatabase.getReference();
    }

    private void onClickview() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUserToFirebase();
            }
        });
    }

    private void registerUserToFirebase() {
        String user = username.getText().toString();
        String email = gmail.getText().toString();
        String passw = password.getText().toString();
        String repass = repassword.getText().toString();
        if (TextUtils.isEmpty(user)) {
            username.setError("please enter username");
            return;
        }
        else if(TextUtils.isEmpty(email)) {
            gmail.setError("please Enter Email");
        }
        else if(TextUtils.isEmpty(email)) {
            gmail.setError("please Enter Email");
        }
        else if(TextUtils.isEmpty(passw)) {
            gmail.setError("please Enter Password");
        }
        else if(TextUtils.isEmpty(repass)) {
            gmail.setError("please Enter Re-Enter Password");
        }
        else if(!repass.equals(passw)) {
            Toast.makeText(getContext()," Please Enter Same Password",Toast.LENGTH_LONG).show();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(email,repass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        LoginRegiUserModel obj=new LoginRegiUserModel(user,email,repass);
                        String id=task.getResult().getUser().getUid();

                        loginRegiReference.child("Users").child(id).setValue(obj);



                        Toast.makeText(getContext()," User Register Seccessfully",Toast.LENGTH_LONG).show();
                }
                    else
                    {
                        Toast.makeText(getContext()," Error:"+ task.getException(),Toast.LENGTH_LONG).show();
                        Log.v("Tag"," "+ task.getException());
                    }
            }});
        }



    }

}