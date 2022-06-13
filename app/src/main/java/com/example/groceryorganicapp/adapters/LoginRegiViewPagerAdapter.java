package com.example.groceryorganicapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.groceryorganicapp.fragments.LoginFragment;
import com.example.groceryorganicapp.fragments.RegisterFragment;

public class LoginRegiViewPagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public LoginRegiViewPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginFragment loginFragment=new LoginFragment();
                return loginFragment;
            case 1:
                RegisterFragment registerFragment=new RegisterFragment();
                return registerFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
