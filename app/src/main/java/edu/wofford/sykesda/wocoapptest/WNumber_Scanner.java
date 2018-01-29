package edu.wofford.sykesda.wocoapptest;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class WNumber_Scanner extends AppCompatActivity {

    SurfaceView cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnumber__scanner);

        cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
        createCameraSource();
    }

    private void createCameraSource() {
        //Build barcode detector, limiting scanning to 1-dimensional barcode formats
        //TODO determine the type of 1D barcodes used on student IDs. Formats should be limited to those used to improve performance
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.CODABAR | Barcode.UPC_A | Barcode.UPC_E | Barcode.EAN_8 | Barcode.EAN_13 | Barcode.ISBN | Barcode.CODE_39 | Barcode.CODE_93 |Barcode.CODE_128 | Barcode.ITF)
                .build();
        //Build camera source from phone's camera
        final CameraSource camSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setRequestedFps(15.0f)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true)
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    Toast toast = Toast.makeText(getApplicationContext(), "Please enable camera permission", Toast.LENGTH_LONG);
                    toast.show();

                    return;
                }
                try {
                    camSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                camSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> barcodes = detections.getDetectedItems();
                //Return to previous activity automatically when a barcode is detected
                if(barcodes.size()>0) {
                    Intent intent = new Intent();
                    //Send value back to previous activity
                    intent.putExtra("barcode", barcodes.valueAt(0));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
