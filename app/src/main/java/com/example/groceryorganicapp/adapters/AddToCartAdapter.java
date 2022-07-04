package com.example.groceryorganicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.models.AddToCart;

import java.nio.Buffer;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.zip.Inflater;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {
    List<AddToCart> addToCartList;
    Context context;
    int ta=0;

    public AddToCartAdapter(List<AddToCart> addToCartList, Context context) {
        this.addToCartList = addToCartList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.cardview_addtocart_rv,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {
               AddToCart addToCart=addToCartList.get(position);
               holder.name.setText(addToCart.getName());
               holder.price.setText(addToCart.getPrice());
               holder.quantity.setText(addToCart.getQuantity());
               int p=Integer.valueOf(addToCart.getPrice());
               int q=Integer.valueOf(addToCart.getQuantity());
               int total=p*q;
               ta=ta+total;
               holder.totalAmount.setText(Integer.toString(total));
               holder.delete.setImageResource(R.drawable.ic_clear_focused);

               holder.delete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       ta=ta-total;
                       int i=holder.getAdapterPosition();
                       addToCartList.remove(i);
                       notifyItemRemoved(i);
                       notifyItemRangeChanged(i,addToCartList.size());
                   }

               });



    }

    @Override
    public int getItemCount() {
        return addToCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,quantity,totalAmount;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.orgoName);
            price=itemView.findViewById(R.id.orgoPrice);
            quantity=itemView.findViewById(R.id.orgoQuantity);
            totalAmount=itemView.findViewById(R.id.totalAmount);
            delete=itemView.findViewById(R.id.deleteAddProduct);

        }
    }
    public List<AddToCart>  getTotalAmount()
    {
        return addToCartList;
    }

}
