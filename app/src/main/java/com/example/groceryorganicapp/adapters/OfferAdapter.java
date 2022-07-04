package com.example.groceryorganicapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.groceryorganicapp.R;
import com.example.groceryorganicapp.models.IntroModel;
import com.example.groceryorganicapp.models.OfferModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class OfferAdapter extends SliderViewAdapter<OfferAdapter.SliderAdapterViewHolder> {

    // list for storing urls of images.
    Context context;
    private final List<OfferModel> mSliderItems;

    // Constructor
    public OfferAdapter(Context context, List<OfferModel> sliderDataArrayList) {
        this.context=context;
        this.mSliderItems = sliderDataArrayList;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_offer, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final OfferModel sliderItem = mSliderItems.get(position);

        // Glide is use to load image
        // from url in your imageview.
      Glide.with(context).load(sliderItem.getImgUrl()).into(viewHolder.imageViewBackground);
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.introimage);
            this.itemView = itemView;
        }
    }
}