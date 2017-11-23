package com.example.android.softvisitingcardapp.category;

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
import com.example.android.softvisitingcardapp.models.Category;
import com.example.android.softvisitingcardapp.models.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dot Net on 22/11/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements Filterable {
    private ArrayList<Category> categoriesArrayList, categoriesFilteredArrayList;
    private Context mCtx;

    public CategoryAdapter(ArrayList<Category> arrayList, Context mCtx) {
        this.categoriesArrayList = arrayList;
        this.categoriesFilteredArrayList = arrayList;
        this.mCtx = mCtx;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_categories, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, int position) {
        final Category category = categoriesFilteredArrayList.get(position);
        holder.textViewBrandName.setText(category.getName());
        holder.textViewBrandDetails.setText(category.getDetails());

        holder.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, CategoryEditActivity.class);
                intent.putExtra("categoryId", category.getId());
                intent.putExtra("categoryName", category.getName());
                intent.putExtra("categoryDetails", category.getDetails());
                mCtx.startActivity(intent);
            }
        });

        holder.deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mCtx);

                View promptsView = li.inflate(R.layout.dialog_delete_list_item, null);

                TextView promptTextView = (TextView) promptsView.findViewById(R.id.prompt_text_view);
                promptTextView.setText("Do you really want to delete" + category.getName() + "?");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // delete brand
                                        deleteListItem(category.getId(), category.getName());
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
                Intent intent = new Intent(mCtx, CategoryDetailActivity.class);
                intent.putExtra("categoryId", category.getId());
                intent.putExtra("categoryName", category.getName());
                intent.putExtra("categoryDetails", category.getDetails());
                mCtx.startActivity(intent);
            }
        });
    }

    public void deleteListItem(int categoryId, final String categoryName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.deleteCategory(categoryId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is successful
                Toast.makeText(mCtx, categoryName + " has been deleted successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, HomeActivity.class);
                intent.putExtra("fragmentToTrigger", "CategoryList");
                mCtx.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // show some message after failure
                Toast.makeText(mCtx, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    categoriesFilteredArrayList = categoriesArrayList;
                } else {

                    ArrayList<Category> filteredList = new ArrayList<>();

                    for (Category category : categoriesArrayList) {

                        if (category.getName().toLowerCase().contains(charString) || category.getDetails().toLowerCase().contains(charString)) {

                            filteredList.add(category);
                        }
                    }

                    categoriesFilteredArrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = categoriesFilteredArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                categoriesFilteredArrayList = (ArrayList<Category>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return categoriesFilteredArrayList.size();
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
}
