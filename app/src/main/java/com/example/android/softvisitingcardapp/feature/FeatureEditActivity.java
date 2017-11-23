package com.example.android.softvisitingcardapp.feature;

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
import com.example.android.softvisitingcardapp.category.CategoryEditActivity;
import com.example.android.softvisitingcardapp.models.Category;
import com.example.android.softvisitingcardapp.models.Feature;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeatureEditActivity extends AppCompatActivity {
    private Button editFeatureButton;

    private EditText featureNameEditText, featureRamEditText, featureRomEditText;
    private EditText featureProcessorEditText, featureOsEditText, featureFrontCameraEditText;
    private EditText featureBackCameraEditText, featureBatteryEditText, featureCpuEditText;
    private EditText featureGpuEditText, featureSimEditText, featureScreenEditText;
    private EditText featureWarrantyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_edit);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int featureId = extras.getInt("featureId");
        final String featureName = extras.getString("featureName");
        final String featureRam = extras.getString("featureRam");
        final String featureRom = extras.getString("featureRom");
        final String featureProcessor = extras.getString("featureProcessor");
        final String featureOs = extras.getString("featureOs");
        final String featureFrontCamera = extras.getString("featureFrontCamera");
        final String featureBackCamera = extras.getString("featureBackCamera");
        final String featureBattery = extras.getString("featureBattery");
        final String featureCpu = extras.getString("featureCpu");
        final String featureGpu = extras.getString("featureGpu");
        final String featureSim = extras.getString("featureSim");
        final String featureScreen = extras.getString("featureScreen");
        final String featureWarranty = extras.getString("featureWarranty");

        initializeViews();

        featureNameEditText.setText(featureName);
        featureRamEditText.setText(featureRam);
        featureRomEditText.setText(featureRom);
        featureProcessorEditText.setText(featureProcessor);
        featureOsEditText.setText(featureOs);
        featureFrontCameraEditText.setText(featureFrontCamera);
        featureBackCameraEditText.setText(featureBackCamera);
        featureBatteryEditText.setText(featureBattery);
        featureCpuEditText.setText(featureCpu);
        featureGpuEditText.setText(featureGpu);
        featureSimEditText.setText(featureSim);
        featureScreenEditText.setText(featureScreen);
        featureWarrantyEditText.setText(featureWarranty);

        editFeatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListItem(featureId);
            }
        });
    }

    private void initializeViews() {
        editFeatureButton = findViewById(R.id.edit_feature_button);
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

    public void editListItem(int featureId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        String updatedFeatureName = featureNameEditText.getText().toString().trim();
        String updatedFeatureRam = featureRamEditText.getText().toString().trim();
        String updatedFeatureRom = featureRomEditText.getText().toString().trim();
        String updatedFeatureProcessor = featureProcessorEditText.getText().toString().trim();
        String updatedFeatureOs = featureOsEditText.getText().toString().trim();
        String updatedFeatureFrontCamera = featureFrontCameraEditText.getText().toString().trim();
        String updatedFeatureBackCamera = featureBackCameraEditText.getText().toString().trim();
        String updatedFeatureBattery = featureBatteryEditText.getText().toString().trim();
        String updatedFeatureCpu = featureCpuEditText.getText().toString().trim();
        String updatedFeatureGpu = featureGpuEditText.getText().toString().trim();
        String updatedFeatureSim = featureSimEditText.getText().toString().trim();
        String updatedFeatureScreen = featureScreenEditText.getText().toString().trim();
        String updatedFeatureWarranty = featureWarrantyEditText.getText().toString().trim();

        Call<Result> call = service.editFeature(featureId, updatedFeatureName, updatedFeatureRam,
                updatedFeatureRom, updatedFeatureProcessor, updatedFeatureOs,
                updatedFeatureFrontCamera, updatedFeatureBackCamera, updatedFeatureBattery,
                updatedFeatureCpu, updatedFeatureGpu, updatedFeatureSim, updatedFeatureScreen,
                updatedFeatureWarranty);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is

                if (!response.body().getError()) {
                    Feature feature = response.body().getFeature();
                    featureNameEditText.setText(feature.getName());
                    featureRamEditText.setText(feature.getRam());
                    featureRomEditText.setText(feature.getRom());
                    featureProcessorEditText.setText(feature.getProcessor());
                    featureOsEditText.setText(feature.getOs());
                    featureFrontCameraEditText.setText(feature.getFrontCamera());
                    featureBackCameraEditText.setText(feature.getBackCamera());
                    featureBatteryEditText.setText(feature.getBattery());
                    featureCpuEditText.setText(feature.getCpu());
                    featureGpuEditText.setText(feature.getGpu());
                    featureSimEditText.setText(feature.getSim());
                    featureScreenEditText.setText(feature.getScreen());
                    featureWarrantyEditText.setText(feature.getWarranty());
                    Toast.makeText(FeatureEditActivity.this, "Details Edit successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(FeatureEditActivity.this, HomeActivity.class);
                    intent.putExtra("fragmentToTrigger", "FeatureList");
                    startActivity(intent);
                } else {
                    Toast.makeText(FeatureEditActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // show some message after failure
                Toast.makeText(FeatureEditActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
