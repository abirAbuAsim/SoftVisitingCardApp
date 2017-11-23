package com.example.android.softvisitingcardapp.feature;

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
import com.example.android.softvisitingcardapp.category.CategoryEditActivity;
import com.example.android.softvisitingcardapp.models.Category;
import com.example.android.softvisitingcardapp.models.Feature;
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

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> implements Filterable {
    private ArrayList<Feature> featuresArrayList, featuresFilteredArrayList;
    private Context mCtx;

    public FeatureAdapter(ArrayList<Feature> arrayList, Context mCtx) {
        this.featuresArrayList = arrayList;
        this.featuresFilteredArrayList = arrayList;
        this.mCtx = mCtx;
    }

    @Override
    public FeatureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_features, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FeatureAdapter.ViewHolder holder, int position) {
        final Feature feature = featuresFilteredArrayList.get(position);
        holder.textViewBrandName.setText(feature.getName());
        holder.textViewBrandRam.setText(feature.getRam());
        holder.textViewBrandRom.setText(feature.getRom());

        holder.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, FeatureEditActivity.class);
                intent.putExtra("featureId", feature.getId());
                intent.putExtra("featureName", feature.getName());
                intent.putExtra("featureRam", feature.getRam());
                intent.putExtra("featureRom", feature.getRom());
                intent.putExtra("featureProcessor", feature.getProcessor());
                intent.putExtra("featureOs", feature.getOs());
                intent.putExtra("featureFrontCamera", feature.getFrontCamera());
                intent.putExtra("featureBackCamera", feature.getBackCamera());
                intent.putExtra("featureBattery", feature.getBattery());
                intent.putExtra("featureCpu", feature.getCpu());
                intent.putExtra("featureGpu", feature.getGpu());
                intent.putExtra("featureSim", feature.getSim());
                intent.putExtra("featureScreen", feature.getScreen());
                intent.putExtra("featureWarranty", feature.getWarranty());
                mCtx.startActivity(intent);
            }
        });

        holder.deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mCtx);

                View promptsView = li.inflate(R.layout.dialog_delete_list_item, null);

                TextView promptTextView = (TextView) promptsView.findViewById(R.id.prompt_text_view);
                promptTextView.setText("Do you really want to delete" + feature.getName() + "?");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // delete brand
                                        deleteListItem(feature.getId(), feature.getName());
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
                Intent intent = new Intent(mCtx, FeatureDetailActivity.class);
                intent.putExtra("featureId", feature.getId());
                intent.putExtra("featureName", feature.getName());
                intent.putExtra("featureRam", feature.getRam());
                intent.putExtra("featureRom", feature.getRom());
                intent.putExtra("featureProcessor", feature.getProcessor());
                intent.putExtra("featureOs", feature.getOs());
                intent.putExtra("featureFrontCamera", feature.getFrontCamera());
                intent.putExtra("featureBackCamera", feature.getBackCamera());
                intent.putExtra("featureBattery", feature.getBattery());
                intent.putExtra("featureCpu", feature.getCpu());
                intent.putExtra("featureGpu", feature.getGpu());
                intent.putExtra("featureSim", feature.getSim());
                intent.putExtra("featureScreen", feature.getScreen());
                intent.putExtra("featureWarranty", feature.getWarranty());
                mCtx.startActivity(intent);
            }
        });
    }

    public void deleteListItem(int featureId, final String featureName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.deleteFeature(featureId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is successful
                Toast.makeText(mCtx, featureName + " has been deleted successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, HomeActivity.class);
                intent.putExtra("fragmentToTrigger", "FeatureList");
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

                    featuresFilteredArrayList = featuresArrayList;
                } else {

                    ArrayList<Feature> filteredList = new ArrayList<>();

                    for (Feature feature : featuresArrayList) {

                        if (feature.getName().toLowerCase().contains(charString) || feature.getRam().toLowerCase().contains(charString)) {

                            filteredList.add(feature);
                        }
                    }

                    featuresFilteredArrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = featuresFilteredArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                featuresFilteredArrayList = (ArrayList<Feature>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return featuresFilteredArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBrandName, textViewBrandRam, textViewBrandRom;
        public ImageButton editImageButton, deleteImageButton;
        public LinearLayout listTextContainerLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            editImageButton = itemView.findViewById(R.id.edit_image_button);
            deleteImageButton = itemView.findViewById(R.id.delete_image_button);

            textViewBrandName = itemView.findViewById(R.id.feature_name_text_view);
            textViewBrandRam = itemView.findViewById(R.id.feature_ram_text_view);
            textViewBrandRom = itemView.findViewById(R.id.feature_rom_text_view);

            listTextContainerLinearLayout = itemView.findViewById(R.id.list_text_container_layout);
        }
    }
}
