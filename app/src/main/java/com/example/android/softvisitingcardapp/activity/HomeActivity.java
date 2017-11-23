package com.example.android.softvisitingcardapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.brand.BrandListFragment;
import com.example.android.softvisitingcardapp.category.CategoryListFragment;
import com.example.android.softvisitingcardapp.feature.FeatureListFragment;
import com.example.android.softvisitingcardapp.fragment.HomeMenuFragment;
import com.example.android.softvisitingcardapp.fragment.ProfileFragment;
import com.example.android.softvisitingcardapp.helper.SharedPrefManager;
import com.example.android.softvisitingcardapp.supplier.SupplierListFragment;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewName;
    private String fragmentToTrigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            fragmentToTrigger = extras.getString("fragmentToTrigger");
        }



        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }

        View headerView = navigationView.getHeaderView(0);
        textViewName = headerView.findViewById(R.id.textViewName);
        textViewName.setText(SharedPrefManager.getInstance(this).getUser().getName());

        if(fragmentToTrigger != null) {
            switch (fragmentToTrigger){
                case "SupplierList":
                    displaySelectedScreen(R.id.nav_supplier);
                    break;
                case "BrandList":
                    displaySelectedScreen(R.id.nav_brand_list);
                    break;
                case "CategoryList":
                    displaySelectedScreen(R.id.nav_category);
                    break;
                case "FeatureList":
                    displaySelectedScreen(R.id.nav_feature);
                    break;
            }

        } else{
            //loading home fragment by default
            displaySelectedScreen(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeMenuFragment();
                break;
            case R.id.nav_supplier:
                fragment = new SupplierListFragment();
                break;
            case R.id.nav_brand_list:
                fragment = new BrandListFragment();
                break;
            case R.id.nav_category:
                fragment = new CategoryListFragment();
                break;
            case R.id.nav_feature:
                fragment = new FeatureListFragment();
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
}
