package com.example.android.softvisitingcardapp.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.supplier.SupplierDetailActivity;
import com.example.android.softvisitingcardapp.supplier.SupplierEditActivity;

public class FeatureDetailActivity extends AppCompatActivity {
    private TextView featureNameTextView, featureRamTextView, featureRomTextView;
    private TextView featureProcessorTextView, featureOsTextView, featureFrontCameraTextView;
    private TextView featureBackCameraTextView, featureBatteryTextView, featureCpuTextView;
    private TextView featureGpuTextView, featureSimTextView, featureScreenTextView;
    private TextView featureWarrantyTextView;

    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_detail);

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

        setDataInViews(featureName, featureRam, featureRom, featureProcessor,
                featureOs, featureFrontCamera, featureBackCamera, featureBattery,
                featureCpu, featureGpu, featureSim, featureScreen, featureWarranty);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeatureDetailActivity.this, FeatureEditActivity.class);
                intent.putExtra("featureId", featureId);
                intent.putExtra("featureName", featureName);
                intent.putExtra("featureRam", featureRam);
                intent.putExtra("featureRom", featureRom);
                intent.putExtra("featureProcessor", featureProcessor);
                intent.putExtra("featureOs", featureOs);
                intent.putExtra("featureFrontCamera", featureFrontCamera);
                intent.putExtra("featureBackCamera", featureBackCamera);
                intent.putExtra("featureBattery", featureBattery);
                intent.putExtra("featureCpu", featureCpu);
                intent.putExtra("featureGpu", featureGpu);
                intent.putExtra("featureSim", featureSim);
                intent.putExtra("featureScreen", featureScreen);
                intent.putExtra("featureWarranty", featureWarranty);
                startActivity(intent);
            }
        });
    }

    private void initializeViews() {
        editButton = findViewById(R.id.edit_button);

        featureNameTextView = findViewById(R.id.feature_name_text_view);
        featureRamTextView = findViewById(R.id.feature_ram_text_view);
        featureRomTextView = findViewById(R.id.feature_rom_text_view);
        featureProcessorTextView = findViewById(R.id.feature_processor_text_view);
        featureOsTextView = findViewById(R.id.feature_os_text_view);
        featureFrontCameraTextView = findViewById(R.id.feature_front_camera_text_view);
        featureBackCameraTextView = findViewById(R.id.feature_back_camera_text_view);
        featureBatteryTextView = findViewById(R.id.feature_battery_text_view);
        featureCpuTextView = findViewById(R.id.feature_cpu_text_view);
        featureGpuTextView = findViewById(R.id.feature_gpu_text_view);
        featureSimTextView = findViewById(R.id.feature_sim_text_view);
        featureScreenTextView = findViewById(R.id.feature_screen_text_view);
        featureWarrantyTextView = findViewById(R.id.feature_warranty_text_view);
    }

    private void setDataInViews(String featureName, String featureRam, String featureRom, String featureProcessor,
                                String featureOs, String featureFrontCamera, String featureBackCamera, String featureBattery,
                                String featureCpu, String featureGpu, String featureSim, String featureScreen, String featureWarranty){

        featureNameTextView.setText(featureName);
        featureRamTextView.setText(featureRam);
        featureRomTextView.setText(featureRom);
        featureProcessorTextView.setText(featureProcessor);
        featureOsTextView.setText(featureOs);
        featureFrontCameraTextView.setText(featureFrontCamera);
        featureBackCameraTextView.setText(featureBackCamera);
        featureBatteryTextView.setText(featureBattery);
        featureCpuTextView.setText(featureCpu);
        featureGpuTextView.setText(featureGpu);
        featureSimTextView.setText(featureSim);
        featureScreenTextView.setText(featureScreen);
        featureWarrantyTextView.setText(featureWarranty);
    }
}
