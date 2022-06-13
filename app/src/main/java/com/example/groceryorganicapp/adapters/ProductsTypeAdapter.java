package com.example.groceryorganicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.models.ProductModel;

import org.w3c.dom.Text;

import java.util.List;

public class ProductsTypeAdapter extends RecyclerView.Adapter<ProductsTypeAdapter.ViewHolder> {

    Context context;
    List<ProductModel> list;

    public ProductsTypeAdapter(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductsTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cardview_produts_rv,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsTypeAdapter.ViewHolder holder, int position) {

        ProductModel productModel=list.get(position);
        holder.textView.setText(productModel.getProdName());
        Glide.with(context).load(productModel.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  static  class  ViewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.productsimage);
            textView=itemView.findViewById(R.id.prodName);
        }
    }
}
