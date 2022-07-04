package com.example.groceryorganicapp.activities;

import static android.net.Uri.parse;
import static androidx.navigation.Navigation.findNavController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.fragments.AddFragment;
import com.example.groceryorganicapp.fragments.CartFragment;
import com.example.groceryorganicapp.fragments.HomeFragment;
import com.example.groceryorganicapp.fragments.SearchFragment;
import com.example.groceryorganicapp.models.LoginRegiUserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {


private static int  PIC_IMAGE=100;
    ImageView selectProfileImage,setProfileImage;
    Uri profileImagePath;
    Bitmap   profileImageStore,profilebitmapimage;


    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth firebaseAuth;
    DatabaseReference dr;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
        toolbar();
        drawer();
        navigationViewItemSelect();
        bottomNavigationView();

        onClick();



    }

    private void toolbar()
    {


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    private void findViewById()
    {

        bottomNavigationView=findViewById(R.id.bottomnavigation);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation_view);
        drawerLayout=findViewById(R.id.drawerlayout);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        fragmentContainerView=findViewById(R.id.fragmentContainerView);



        View header = navigationView.getHeaderView(0);
        selectProfileImage=header.findViewById(R.id.selectimage);
        setProfileImage=header.findViewById(R.id.userimage);





    }
    private void onClick()
    {
        selectProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        PIC_IMAGE);

            }
        });
        setProfileImage();
    }

    public void drawer()
    {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        return true;
//    }












    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
           return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigationViewItemSelect() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
              /*  if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true); */

                // I THINK THAT I NEED EDIT HERE...


                //Closing drawer on item click
                drawerLayout.closeDrawers();


                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    //Replacing the main content with ContentFragment
                    case R.id.home_nv:
                        Toast.makeText(MainActivity.this,"ksjdfkjsakdlfj",Toast.LENGTH_LONG).show();
                        return  true;

                }
                return false;


            }

        });
    }

    // for bottomNavigationItemSelected
    private void bottomNavigationView()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(MainActivity.this);
        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).commit();
                return true;

            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new SearchFragment()).commit();
                return true;

            case R.id.cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new CartFragment()).commit();
                return true;


        }
        return false;
    }

    private void setProfileImage()
    {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PIC_IMAGE && resultCode == RESULT_OK ) {

                profileImagePath=data.getData();

                  profileImageStore=MediaStore.Images.Media.getBitmap(getContentResolver(),profileImagePath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                profileImageStore.compress(Bitmap.CompressFormat.PNG, 100, stream);
                storeProfileImageToStorage(profileImagePath);
               // setProfileImage.setImageBitmap(profileImageStore);



            }

        }catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }





    }
    private void storeProfileImageToStorage(Uri profileimg) {

        StorageReference storageReference=firebaseStorage.getReference().child("profileImage/"  + UUID.randomUUID().toString());
        storageReference.putFile(profileimg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast
                        .makeText(MainActivity.this,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                        // Error, Image not uploaded

                        Toast
                                .makeText(MainActivity.this,
                                        "Failed " + e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });

    }
}