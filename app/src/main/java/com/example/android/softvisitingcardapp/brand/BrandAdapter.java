package com.example.android.softvisitingcardapp.brand;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.activity.HomeActivity;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.models.Brand;
import com.example.android.softvisitingcardapp.models.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Belal on 14/04/17.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> implements Filterable {

    private ArrayList<Brand> brandsArrayList, brandsFilteredArrayList;
    private Context mCtx;

    public BrandAdapter(ArrayList<Brand> arrayList, Context mCtx) {
        this.brandsArrayList = arrayList;
        this.brandsFilteredArrayList = arrayList;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_brands, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BrandAdapter.ViewHolder holder, int position) {
        final Brand brand = brandsFilteredArrayList.get(position);
        holder.textViewBrandName.setText(brand.getName());
        holder.textViewBrandDetails.setText(brand.getDetails());

        holder.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, BrandEditActivity.class);
                intent.putExtra("brandId", brand.getId());
                intent.putExtra("brandName", brand.getName());
                intent.putExtra("brandDetails", brand.getDetails());
                mCtx.startActivity(intent);
            }
        });

        holder.deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mCtx);

                View promptsView = li.inflate(R.layout.dialog_delete_list_item, null);

                TextView promptTextView = (TextView) promptsView.findViewById(R.id.prompt_text_view);
                promptTextView.setText("Do you really want to delete" + brand.getName() + "?");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // delete brand
                                        deleteListItem(brand.getId(), brand.getName());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

        holder.listTextContainerLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, BrandDetailActivity.class);
                intent.putExtra("brandId", brand.getId());
                intent.putExtra("brandName", brand.getName());
                intent.putExtra("brandDetails", brand.getDetails());
                mCtx.startActivity(intent);
            }
        });
    }



    public void deleteListItem(int brandId, final String brandName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.deleteBrand(brandId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is successful
                Toast.makeText(mCtx, brandName + " has been deleted successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // show some message after failure
                Toast.makeText(mCtx, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(mCtx, HomeActivity.class);
        intent.putExtra("fragmentToTrigger", "BrandList");
        mCtx.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return brandsFilteredArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBrandName, textViewBrandDetails;
        public ImageButton editImageButton, deleteImageButton;
        public LinearLayout listTextContainerLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            editImageButton = itemView.findViewById(R.id.edit_image_button);
            deleteImageButton = itemView.findViewById(R.id.delete_image_button);

            textViewBrandName = itemView.findViewById(R.id.brand_name_text_view);
            textViewBrandDetails = itemView.findViewById(R.id.brand_details_text_view);
            listTextContainerLinearLayout = itemView.findViewById(R.id.list_text_container_layout);
        }
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    brandsFilteredArrayList = brandsArrayList;
                } else {

                    ArrayList<Brand> filteredList = new ArrayList<>();

                    for (Brand brand : brandsArrayList) {

                        if (brand.getName().toLowerCase().contains(charString) || brand.getDetails().toLowerCase().contains(charString)) {

                            filteredList.add(brand);
                        }
                    }

                    brandsFilteredArrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = brandsFilteredArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                brandsFilteredArrayList = (ArrayList<Brand>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}