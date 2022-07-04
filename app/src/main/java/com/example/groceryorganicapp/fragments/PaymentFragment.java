package com.example.groceryorganicapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.adapters.AddToCartAdapter;
import com.example.groceryorganicapp.models.AddToCart;

import java.util.ArrayList;
import java.util.List;


public class PaymentFragment extends Fragment {

    List<AddToCart> addToCartList;
    RecyclerView addToCartRV;
    AddToCartAdapter addToCartAdapter;
    Button payBill;
    TextView totalAmount;
    PaymentFragment(List<AddToCart> list)
    {
        addToCartList=list;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_payment, container, false);

        addToCartRV=view.findViewById(R.id.addToCartRv);
        payBill=view.findViewById(R.id.payBill);
        totalAmount=view.findViewById(R.id.totalAmount);
        payBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Your Organic Produts Bill Payment SuccessFull",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayoutManager llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        addToCartRV.setLayoutManager(llm);

        addToCartAdapter=new AddToCartAdapter(addToCartList,getContext());
        addToCartRV.setAdapter(addToCartAdapter);
        List<AddToCart> talist=new ArrayList<>();
              talist  =addToCartAdapter.getTotalAmount();
        int ta=0;
        for(int i=0;i<talist.size();i++)
        {

        }
        totalAmount.setText(String.valueOf(ta));







        return view;
    }
}