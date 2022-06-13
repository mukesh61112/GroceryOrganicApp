package com.example.groceryorganicapp.fragments;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.groceryorganicapp.activities.MainActivity;
import com.example.groceryorganicapp.models.LoginRegiUserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {


    EditText email,password;
    Button login;
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);

        findViewById(v);
        onClickonView();

        return v;
    }
    private void findViewById(View v)
    {
        email=v.findViewById(R.id.email);
        password=v.findViewById(R.id.password);
        login=v.findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    private void onClickonView()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserTofirebase();
            }
        });
    }
    private void loginUserTofirebase()
    {
        String eml=email.getText().toString();
        String passw = password.getText().toString();

         if(TextUtils.isEmpty(eml)) {
            email.setError("please Enter Email");
            return;
        }
         if(TextUtils.isEmpty(passw)) {
            password.setError("please Enter Enter Password");
            return;
        }

            firebaseAuth.signInWithEmailAndPassword(eml,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext()," User Login Seccessfully",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getContext()," Error:"+ task.getException(),Toast.LENGTH_LONG).show();
                        Log.v("Tag"," "+ task.getException());
                    }
                }
            });
         }

}