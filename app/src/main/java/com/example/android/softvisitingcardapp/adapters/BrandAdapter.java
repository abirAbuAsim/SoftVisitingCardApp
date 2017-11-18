package com.example.android.softvisitingcardapp.adapters;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.models.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 14/04/17.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private ArrayList<Brand> brands;
    private Context mCtx;

    public BrandAdapter(ArrayList<Brand> brands, Context mCtx) {
        this.brands = brands;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_brands, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BrandAdapter.ViewHolder holder, int position) {
        final Brand brand = brands.get(position);
        holder.textViewBrandName.setText(brand.getName());
        holder.textViewBrandDetails.setText(brand.getDetails());

        holder.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBrandName, textViewBrandDetails;
        public ImageButton editImageButton, deleteImageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            editImageButton = (ImageButton) itemView.findViewById(R.id.edit_image_button);

            textViewBrandName = (TextView) itemView.findViewById(R.id.brand_name_text_view);
            textViewBrandDetails = (TextView) itemView.findViewById(R.id.brand_details_text_view);
        }
    }
}