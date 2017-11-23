package com.example.android.softvisitingcardapp.feature;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.activity.HomeActivity;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.category.CategoryAddActivity;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeatureAddActivity extends AppCompatActivity implements View.OnClickListener{
    private Button addFeatureButton;

    private EditText featureNameEditText, featureRamEditText, featureRomEditText;
    private EditText featureProcessorEditText, featureOsEditText, featureFrontCameraEditText;
    private EditText featureBackCameraEditText, featureBatteryEditText, featureCpuEditText;
    private EditText featureGpuEditText, featureSimEditText, featureScreenEditText;
    private EditText featureWarrantyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_add);

        this.setTitle("Add Feature");

        initializeViews();

        addFeatureButton.setOnClickListener(this);
    }

    private void initializeViews() {
        addFeatureButton = findViewById(R.id.add_feature_button);
        featureNameEditText = findViewById(R.id.feature_name_edit_text);
        featureRamEditText = findViewById(R.id.feature_ram_edit_text);
        featureRomEditText = findViewById(R.id.feature_rom_edit_text);
        featureProcessorEditText = findViewById(R.id.feature_processor_edit_text);
        featureOsEditText = findViewById(R.id.feature_os_edit_text);
        featureFrontCameraEditText = findViewById(R.id.feature_front_camera_edit_text);
        featureBackCameraEditText = findViewById(R.id.feature_back_camera_edit_text);
        featureBatteryEditText = findViewById(R.id.feature_battery_edit_text);
        featureCpuEditText = findViewById(R.id.feature_cpu_edit_text);
        featureGpuEditText = findViewById(R.id.feature_gpu_edit_text);
        featureSimEditText = findViewById(R.id.feature_sim_edit_text);
        featureScreenEditText = findViewById(R.id.feature_screen_edit_text);
        featureWarrantyEditText = findViewById(R.id.feature_warranty_edit_text);
    }

    private void addCategory() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        String featureName = featureRamEditText.getText().toString().trim();
        String featureRam = featureRamEditText.getText().toString().trim();
        String featureRom = featureRamEditText.getText().toString().trim();
        String featureProcessor = featureRamEditText.getText().toString().trim();
        String featureOs = featureRamEditText.getText().toString().trim();
        String featureFrontCamera = featureRamEditText.getText().toString().trim();
        String featureBackCamera = featureRamEditText.getText().toString().trim();
        String featureBattery = featureRamEditText.getText().toString().trim();
        String featureCpu = featureRamEditText.getText().toString().trim();
        String featureGpu = featureRamEditText.getText().toString().trim();
        String featureSim = featureRamEditText.getText().toString().trim();
        String featureScreen = featureRamEditText.getText().toString().trim();
        String featureWarranty = featureRamEditText.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.addFeatureInfo(featureName, featureRam, featureRom,
                featureProcessor, featureOs, featureFrontCamera, featureBackCamera,
                featureBattery, featureCpu, featureGpu, featureSim, featureScreen,
                featureWarranty);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(FeatureAddActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                emptyEditText();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FeatureAddActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void emptyEditText() {
        featureNameEditText.setText("");
        featureRamEditText.setText("");
        featureRomEditText.setText("");
        featureProcessorEditText.setText("");
        featureOsEditText.setText("");
        featureFrontCameraEditText.setText("");
        featureBackCameraEditText.setText("");
        featureBatteryEditText.setText("");
        featureCpuEditText.setText("");
        featureGpuEditText.setText("");
        featureSimEditText.setText("");
        featureScreenEditText.setText("");
        featureWarrantyEditText.setText("");
    }

    @Override
    public void onClick(View view) {
        if (view == addFeatureButton) {
            addCategory();
            Intent intent = new Intent(FeatureAddActivity.this, HomeActivity.class);
            intent.putExtra("fragmentToTrigger", "FeatureList");
            startActivity(intent);
        }
    }
}
