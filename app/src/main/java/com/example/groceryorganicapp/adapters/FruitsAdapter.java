package com.example.groceryorganicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.models.AddToCart;
import com.example.groceryorganicapp.models.SeachRVModel;

import java.util.ArrayList;
import java.util.List;

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.ViewHolder> {
    List<SeachRVModel> list;
    Context context;
    List<AddToCart> addToCartList=new ArrayList<>();
    List<AddToCart> emptylist=new ArrayList<>();

    public FruitsAdapter(List<SeachRVModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FruitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_search_pro_rv,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsAdapter.ViewHolder holder, int position) {

        SeachRVModel seachRVModel=list.get(position);
        Glide.with(context).load(seachRVModel.getImage()).into(holder.imageView);
        holder.name.setText(seachRVModel.getName());
        holder.price.setText(seachRVModel.getPrice());
        holder.type.setText(seachRVModel.getType());
        int total=Integer.parseInt(seachRVModel.getPrice());
        final int[] count = {0};
        final boolean[] click = {true};


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addToCartList.size()>0)
                     addToCartList.remove(addToCartList.size()-1);
                click[0]=true;
                  if(count[0]<6) {
                      count[0]++;
                      holder.quantity.setText(Integer.toString(count[0]));
                  }
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  click[0]=true;
                   if(addToCartList.size()>0)
                    addToCartList.remove(addToCartList.size()-1);
                   if(count[0] >0)
                   {
                       count[0]--;
                       holder.quantity.setText(Integer.toString(count[0]));
                   }
            }
        });



        holder.addpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click[0]) {

                    click[0] =false;
                    if (count[0] > 0) {
                        int cnt = count[0];
                        String cnts = Integer.toString(cnt);
                        addToCartList.add(new AddToCart(seachRVModel.getName(), seachRVModel.getPrice(), cnts));
                    } else {
                        Toast.makeText(context, "please add first ", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    return;
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
    public List<AddToCart> getFrutAddToCart()
    {
        if(addToCartList.size()>0)
            return  addToCartList;
        return  addToCartList;
    }
}
