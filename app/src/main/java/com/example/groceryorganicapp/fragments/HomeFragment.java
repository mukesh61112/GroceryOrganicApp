package com.example.groceryorganicapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.adapters.IntroAdapter;
import com.example.groceryorganicapp.adapters.OfferAdapter;
import com.example.groceryorganicapp.adapters.ProductsTypeAdapter;
import com.example.groceryorganicapp.models.IntroModel;
import com.example.groceryorganicapp.models.OfferModel;
import com.example.groceryorganicapp.models.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


RecyclerView introRv,productsRv;
SliderView sliderView,offerRv;

List<IntroModel> introlist;
List<OfferModel> offerList;
List<ProductModel> productModelList;

IntroAdapter introAdapter;
OfferAdapter offerAdapter;
ProductsTypeAdapter productsTypeAdapter;



FirebaseFirestore firebaseFirestoreforIntroRV,firebaseFirestoreforProdType,firestoreOffer;
    private DocumentReference document;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        findViewById(v);

       introRvWithFfirestore();
       productsRvWithFirestore();
       offerRvWithFirebase();



        return v;
    }
    private void findViewById(View v){
       // introRv=v.findViewById(R.id.introRv);
      
        firebaseFirestoreforIntroRV=FirebaseFirestore.getInstance();
        firebaseFirestoreforProdType=FirebaseFirestore.getInstance();
        firestoreOffer=FirebaseFirestore.getInstance();
        productsRv=v.findViewById(R.id.productsRv);
        sliderView=v.findViewById(R.id.introRv);
        offerRv=v.findViewById(R.id.offerRv);

    }
    private void introRvWithFfirestore(){
        

        introlist=new ArrayList<IntroModel>();

       /* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        introAdapter=new IntroAdapter(getContext(),rvlist);
        introRv.setLayoutManager(linearLayoutManager);

        introRv.setAdapter(introAdapter);
        getDataFromFFireStoreInIntroRV(); */

        getDataFromFFireStoreInIntroRV();
        introAdapter=new IntroAdapter(getContext(),introlist);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(introAdapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(5);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();



    }
    private void offerRvWithFirebase()
    {
        offerList=new ArrayList<OfferModel>();

       /* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        introAdapter=new IntroAdapter(getContext(),rvlist);
        introRv.setLayoutManager(linearLayoutManager);

        introRv.setAdapter(introAdapter);
        getDataFromFFireStoreInIntroRV(); */

         getDataFromFireStoreInOfferRV();
        offerAdapter=new OfferAdapter(getContext(),offerList);

        offerRv.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
//        offerList.add(new OfferModel("https://th.bing.com/th/id/OIP.-dtpV_XcAejK8OSqrowilwHaEK?pid=ImgDet&rs=1"));
//        offerList.add(new OfferModel("https://th.bing.com/th/id/OIP.-dtpV_XcAejK8OSqrowilwHaEK?pid=ImgDet&rs=1"));
//        offerList.add(new OfferModel("https://th.bing.com/th/id/OIP.-dtpV_XcAejK8OSqrowilwHaEK?pid=ImgDet&rs=1"));

        offerRv.setSliderAdapter(offerAdapter);

        // below method is use to set
        // scroll time in seconds.
        offerRv.setScrollTimeInSec(5);

        // to set it scrollable automatically
        // we use below method.
        offerRv.setAutoCycle(true);

        // to start autocycle below method is used.
        offerRv.startAutoCycle();

    }

    private void getDataFromFFireStoreInIntroRV() {

        firebaseFirestoreforIntroRV.collection("IntroImageSlider").get()
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
                                // list to our object class.
                                IntroModel dataModal = d.<IntroModel>toObject(IntroModel.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                introlist.add(dataModal);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifyDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            introAdapter.notifyDataSetChanged();
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
    private  void getDataFromFireStoreInOfferRV()
    {
        firestoreOffer.collection("Offers").get()
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
                                // list to our object class.

                                OfferModel offerModel=d.<OfferModel>toObject(OfferModel.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                offerList.add(offerModel);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifyDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            offerAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are
                            // displaying a toast message.
                            Toast.makeText(getContext(), "No Offer data found in Database", Toast.LENGTH_SHORT).show();
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
    private  void productsRvWithFirestore(){


        productModelList=new ArrayList<>();
        productsTypeAdapter=new ProductsTypeAdapter(getContext(),productModelList);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        productsRv.setLayoutManager(linearLayoutManager);
        productsRv.setAdapter(productsTypeAdapter);
        productsRv.stopScroll();
        getDatafromFirestoreInProductsTypeRV();


    }
    private void getDatafromFirestoreInProductsTypeRV(){


        firebaseFirestoreforProdType.collection("ProductsTypeRV").get()
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

                                ProductModel productModel=d.toObject(ProductModel.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                              productModelList.add(productModel);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifyDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            productsTypeAdapter.notifyDataSetChanged();
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

}