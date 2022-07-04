package com.example.groceryorganicapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.adapters.DairyAdapter;
import com.example.groceryorganicapp.adapters.FruitsAdapter;
import com.example.groceryorganicapp.adapters.SearchRVAdapter;
import com.example.groceryorganicapp.adapters.VegetablesAdapter;
import com.example.groceryorganicapp.models.AddToCart;
import com.example.groceryorganicapp.models.SeachRVModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView orgoSerchProRv;
    SeachRVModel seachRVModel;
    SearchRVAdapter searchRVAdapter;
    VegetablesAdapter vegetablesAdapter;
    FruitsAdapter fruitsAdapter;
    DairyAdapter dairyAdapter;
    List<SeachRVModel> searchproList;


    ImageView veg,fruit,dairy;
    boolean vegb=false,fruitb=false,dairyb=false;
Button addToCart;

    FirebaseFirestore firebaseFirestore;

    String type=null;
    public SearchFragment(String type) {
        this.type=type;
    }

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);



        findViewById(view);
        imageOnClick();
       setAllProRv();
        addToCartClick();
        return view;
    }
    public void findViewById(View v)
    {
        orgoSerchProRv=v.findViewById(R.id.searchRV);
        firebaseFirestore=FirebaseFirestore.getInstance();
        veg=v.findViewById(R.id.vegetables);
        fruit=v.findViewById(R.id.fruits);
        dairy=v.findViewById(R.id.dairyproducts);
        addToCart=v.findViewById(R.id.addToCart);
    }
    private void imageOnClick()
    {
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fruitb=true;
                searchproList=new ArrayList<>();
                fruitsAdapter=new FruitsAdapter(searchproList,getContext());

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                orgoSerchProRv.setLayoutManager(linearLayoutManager);
                orgoSerchProRv.setAdapter(fruitsAdapter);

                getFruitsFromFirestore();
            }
        });
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vegb=true;
                searchproList=new ArrayList<>();
                vegetablesAdapter=new VegetablesAdapter(searchproList,getContext());

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                orgoSerchProRv.setLayoutManager(linearLayoutManager);
                orgoSerchProRv.setAdapter( vegetablesAdapter);
                getVegetablesFromFirestore();
            }
        });
        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dairyb=true;
                searchproList=new ArrayList<>();
                dairyAdapter=new DairyAdapter(searchproList,getContext());

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                orgoSerchProRv.setLayoutManager(linearLayoutManager);
                orgoSerchProRv.setAdapter( dairyAdapter);
                getDairyProFromFirestore();
            }
        });
    }
    private void setAllProRv()
    {
//        setRv();
//        if(type!=null)
//           getOrgoProSearchDatafromFirestoreInProductsTypeRV(type);
//        else


        if(type=="Vegetables " || type==null)
        {
            vegb=true;
            searchproList=new ArrayList<>();
            vegetablesAdapter=new VegetablesAdapter(searchproList,getContext());

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            orgoSerchProRv.setLayoutManager(linearLayoutManager);
            orgoSerchProRv.setAdapter( vegetablesAdapter);
            getVegetablesFromFirestore();
        }
        else if(type=="Fruits")
        {
            fruitb=true;
            searchproList=new ArrayList<>();
            fruitsAdapter=new FruitsAdapter(searchproList,getContext());

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            orgoSerchProRv.setLayoutManager(linearLayoutManager);
            orgoSerchProRv.setAdapter(fruitsAdapter);

            getFruitsFromFirestore();
        }
        else if(type=="DairyProducts")
        {
            dairyb=true;
            searchproList=new ArrayList<>();
            dairyAdapter=new DairyAdapter(searchproList,getContext());

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            orgoSerchProRv.setLayoutManager(linearLayoutManager);
            orgoSerchProRv.setAdapter( dairyAdapter);
            getDairyProFromFirestore();
        }

    }

    private  void setRv(){


        searchproList=new ArrayList<>();
        searchRVAdapter=new SearchRVAdapter(searchproList,getContext());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        orgoSerchProRv.setLayoutManager(linearLayoutManager);
        orgoSerchProRv.setAdapter(searchRVAdapter);
        //searchproList.add(new SeachRVModel("https://th.bing.com/th/id/OIP.XfSd-YNo58R6hYM3lTSoDQHaJl?pid=ImgDet&rs=1","asdf","20"));


    }
    private void getVegetablesFromFirestore(){


        firebaseFirestore.collection("Vegetables ").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding our
                            // progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing that

                                SeachRVModel seachRVModel=d.toObject(SeachRVModel.class);


                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                searchproList.add(seachRVModel);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifyDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            vegetablesAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are
                            // displaying a toast message.
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // if we do not get any data or any error we are displaying
                        // a toast message that we do not get any data
                        Toast.makeText(getContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getFruitsFromFirestore(){


        firebaseFirestore.collection("Fruits").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding our
                            // progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing that

                                SeachRVModel seachRVModel=d.toObject(SeachRVModel.class);


                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                searchproList.add(seachRVModel);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifyDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            fruitsAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are
                            // displaying a toast message.
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // if we do not get any data or any error we are displaying
                        // a toast message that we do not get any data
                        Toast.makeText(getContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getDairyProFromFirestore(){


        firebaseFirestore.collection("DairyProducts").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding our
                            // progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing that

                                SeachRVModel seachRVModel=d.toObject(SeachRVModel.class);


                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                searchproList.add(seachRVModel);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifyDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            dairyAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are
                            // displaying a toast message.
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // if we do not get any data or any error we are displaying
                        // a toast message that we do not get any data
                        Toast.makeText(getContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
  private void addToCartClick()
  {
      addToCart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              List<AddToCart> list=new ArrayList<>();
              List<AddToCart> veg=new ArrayList<>();
           List<AddToCart> frt=new ArrayList<>();
           List<AddToCart> dry=new ArrayList<>();
           if(vegb)
               veg=vegetablesAdapter.getVegAddToCart();
           if(fruitb)
               frt=fruitsAdapter.getFrutAddToCart();
           if(dairyb)
               dry=dairyAdapter.getDairyAddToCart();



           if(veg.size()>0)
               list.addAll(veg);
           if(frt.size()>0)
              list.addAll(frt);
           if(dry.size()>0)
              list.addAll(dry);

           List<AddToCart> allList = new ArrayList<>(list);
              if(allList.size()>0)
              {
                  AppCompatActivity activity =(AppCompatActivity)view.getContext();

                  activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new PaymentFragment(allList)).addToBackStack("k").commit();
              }
              else{
                  Toast.makeText(getContext(),"please add some organic products",Toast.LENGTH_SHORT).show();
              }

          }
      });
  }
}