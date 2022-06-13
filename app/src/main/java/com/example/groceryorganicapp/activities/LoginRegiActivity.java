package com.example.groceryorganicapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.adapters.LoginRegiViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginRegiActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewpager;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_regi);


        tabLayout=findViewById(R.id.tablayout);
        viewpager=findViewById(R.id.viewpager);
        layout=findViewById(R.id.innerCon);
        layout.setBackgroundColor(Color.TRANSPARENT);
        tabLayout.setBackgroundColor(Color.TRANSPARENT);

        tabLayout.addTab(tabLayout.newTab().setText("LOGIN"));
        tabLayout.addTab(tabLayout.newTab().setText("REGISTER"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //  final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        //   viewPager.setAdapter(adapter);
        final LoginRegiViewPagerAdapter loginRegiViewPagerAdapter=new LoginRegiViewPagerAdapter(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewpager.setAdapter(loginRegiViewPagerAdapter);

        // viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}