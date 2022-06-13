package com.example.groceryorganicapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.groceryorganicapp.R;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser()!=null)
                {

                    Thread td=new Thread() {
                        public void run() {
                            try {
                                //sleep(5);

                            } catch (Exception e) {
                                e.printStackTrace();

                            } finally {
                                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    };td.start();

                }
                else
                {
                    Intent intent=new Intent(SplashActivity.this,LoginRegiActivity.class);
                    startActivity(intent);

                }

            }
        } ,3000);
    }
}