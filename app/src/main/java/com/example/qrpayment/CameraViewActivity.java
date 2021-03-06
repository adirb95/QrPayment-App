package com.example.qrpayment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraViewActivity extends AppCompatActivity {
    SurfaceView surfaceView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    private static final int PERMISSION_REQUEST_CODE = 200;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Lifecycle: ", "CameraViewActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        username = getIntent().getStringExtra("name");
        surfaceView = findViewById(R.id.Camera_preview);
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(1920, 1024).setAutoFocusEnabled(true).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(CameraViewActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CameraViewActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
                return;
                }
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qr_content = detections.getDetectedItems();
                System.out.println("test");
                if (qr_content.size() > 0) {
                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(250);
                    barcodeDetector.release();
                    Intent intent = new Intent(getApplicationContext(), AfterQRscanActivity.class);
                    intent.putExtra("QRDetails", (qr_content.valueAt(0)).displayValue);
                    intent.putExtra("name", username);
                    startActivity(intent);
                }
            }
        });
    }
}