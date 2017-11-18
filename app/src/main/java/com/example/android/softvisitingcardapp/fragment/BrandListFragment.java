package com.example.android.softvisitingcardapp.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.activity.BrandFragment;
import com.example.android.softvisitingcardapp.adapters.BrandAdapter;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.models.Brands;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrandListFragment extends Fragment {

    private RecyclerView recyclerViewUsers;
    private RecyclerView.Adapter adapter;
    public static String userEmail;
    public static int cardId;

    public BrandListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("All Brands");

        recyclerViewUsers = (RecyclerView) view.findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(getActivity(), BrandFragment.class);
                startActivity(intent);
            }
        });
        */



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Brands> call = service.getBrands();

        call.enqueue(new Callback<Brands>() {
            @Override
            public void onResponse(Call<Brands> call, Response<Brands> response) {
                adapter = new BrandAdapter(response.body().getBrands(), getActivity());
                recyclerViewUsers.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Brands> call, Throwable t) {

            }
        });
    }

}
