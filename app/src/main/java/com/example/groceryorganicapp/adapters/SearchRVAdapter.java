package com.example.groceryorganicapp.adapters;

import static java.lang.Integer.valueOf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.models.AddToCart;
import com.example.groceryorganicapp.models.SeachRVModel;

import java.util.ArrayList;
import java.util.List;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder> {
    List<SeachRVModel> list;
    Context context;
    List<AddToCart> addToCartList;


    public SearchRVAdapter(List<SeachRVModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_search_pro_rv,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRVAdapter.ViewHolder holder, int position) {

        SeachRVModel seachRVModel=list.get(position);
        Glide.with(context).load(seachRVModel.getImage()).into(holder.imageView);
        holder.name.setText(seachRVModel.getName());
        holder.price.setText(seachRVModel.getPrice());
        holder.type.setText(seachRVModel.getType());
        int total=Integer.parseInt(seachRVModel.getPrice());
        final int[] count = {0};
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(count[0]<6) {
                      count[0]++;
                      holder.quantity.setText(Integer.toString(count[0]));
                  }
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if(count[0] >0)
                   {
                       count[0]--;
                       holder.quantity.setText(Integer.toString(count[0]));
                   }
            }
        });

        addToCartList=new ArrayList<>();
       holder.addpro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(count[0]>0) {
                   int cnt=count[0];
                   String cnts=Integer.toString(cnt);
                   addToCartList.add(new AddToCart(seachRVModel.getName(), seachRVModel.getPrice(), cnts));
               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,add,minus;
        TextView name,price,type,quantity;
        Button addpro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.orgoImage);
            name=itemView.findViewById(R.id.orgoName);
            price=itemView.findViewById(R.id.orgoPrice);
            type=itemView.findViewById(R.id.protype);
            add=itemView.findViewById(R.id.orgoAdd);
            minus=itemView.findViewById(R.id.orgoMinus);
            quantity=itemView.findViewById(R.id.orgoQuantity);
            addpro=itemView.findViewById(R.id.addpro);


        }
    }
    public List<AddToCart> getAllAddToCart()
    {
           return addToCartList;
    }
}
