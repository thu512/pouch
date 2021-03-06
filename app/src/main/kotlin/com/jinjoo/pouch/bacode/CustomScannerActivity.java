package com.jinjoo.pouch.bacode;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jinjoo.pouch.R;
import com.jinjoo.pouch.activity.Activity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CustomScannerActivity extends Activity implements DecoratedBarcodeView.TorchListener {

    protected final String TAG = "CustomScannerActivity";

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private ImageButton btnSetting, btnSwitchFlash;
    private Boolean switchFlashlightButtonCheck;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scanner);

        switchFlashlightButtonCheck = true;

        exit = findViewById(R.id.exit);
        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);

        if (!hasFlash()) {
            btnSwitchFlash.setVisibility(View.GONE);
        }

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        exit.setOnClickListener(l -> {
            this.finish();
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    /**
     * TorchListener
     */
    @Override
    public void onTorchOn() {
        //btnSwitchFlash.setImageResource(R.drawable.ic_menu_flash);
        switchFlashlightButtonCheck = false;
    }

    @Override
    public void onTorchOff() {
        //btnSwitchFlash.setImageResource(R.drawable.ic_menu_flash);
        switchFlashlightButtonCheck = true;
    }

}
