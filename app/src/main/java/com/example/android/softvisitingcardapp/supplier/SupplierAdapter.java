package com.example.android.softvisitingcardapp.supplier;

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
import com.example.android.softvisitingcardapp.feature.FeatureEditActivity;
import com.example.android.softvisitingcardapp.models.Feature;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.Supplier;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dot Net on 22/11/2017.
 */

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.ViewHolder> implements Filterable {
    private ArrayList<Supplier> suppliersArrayList, suppliersFilteredArrayList;
    private Context mCtx;

    public SupplierAdapter(ArrayList<Supplier> arrayList, Context mCtx) {
        this.suppliersArrayList = arrayList;
        this.suppliersFilteredArrayList = arrayList;
        this.mCtx = mCtx;
    }

    @Override
    public SupplierAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_suppliers, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SupplierAdapter.ViewHolder holder, int position) {
        final Supplier supplier = suppliersFilteredArrayList.get(position);
        holder.textViewSupplierName.setText(supplier.getName());
        holder.textViewSupplierName.setText(supplier.getName());

        holder.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, SupplierEditActivity.class);
                intent.putExtra("supplierId", supplier.getId());
                intent.putExtra("supplierName", supplier.getName());
                intent.putExtra("supplierAddress", supplier.getAddress());
                intent.putExtra("supplierPhoneNo", supplier.getPhoneNumber());
                intent.putExtra("supplierCompanyName", supplier.getCompanyName());


                mCtx.startActivity(intent);
            }
        });

        holder.deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mCtx);

                View promptsView = li.inflate(R.layout.dialog_delete_list_item, null);

                TextView promptTextView = (TextView) promptsView.findViewById(R.id.prompt_text_view);
                promptTextView.setText("Do you really want to delete" + supplier.getName() + "?");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // delete brand
                                        deleteListItem(supplier.getId(), supplier.getName());
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
                Intent intent = new Intent(mCtx, SupplierDetailActivity.class);
                intent.putExtra("supplierId", supplier.getId());
                intent.putExtra("supplierName", supplier.getName());
                intent.putExtra("supplierAddress", supplier.getAddress());
                intent.putExtra("supplierPhoneNo", supplier.getPhoneNumber());
                intent.putExtra("supplierCompanyName", supplier.getCompanyName());

                mCtx.startActivity(intent);
            }
        });
    }

    public void deleteListItem(int supplierId, final String supplierName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.deleteSupplier(supplierId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is successful
                Toast.makeText(mCtx, supplierName + " has been deleted successful", Toast.LENGTH_SHORT).show();
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

                    suppliersFilteredArrayList = suppliersArrayList;
                } else {

                    ArrayList<Supplier> filteredList = new ArrayList<>();

                    for (Supplier supplier : suppliersArrayList) {

                        if (supplier.getName().toLowerCase().contains(charString) || supplier.getName().toLowerCase().contains(charString) || supplier.getCompanyName().toLowerCase().contains(charString)) {

                            filteredList.add(supplier);
                        }
                    }

                    suppliersFilteredArrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suppliersFilteredArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                suppliersFilteredArrayList = (ArrayList<Supplier>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return suppliersFilteredArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewSupplierName, textViewSupplierCompanyName;
        public ImageButton editImageButton, deleteImageButton;
        public LinearLayout listTextContainerLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            editImageButton = itemView.findViewById(R.id.edit_image_button);
            deleteImageButton = itemView.findViewById(R.id.delete_image_button);

            textViewSupplierName = itemView.findViewById(R.id.supplier_name_text_view);
            textViewSupplierCompanyName = itemView.findViewById(R.id.supplier_company_name_text_view);

            listTextContainerLinearLayout = itemView.findViewById(R.id.list_text_container_layout);
        }
    }
}
