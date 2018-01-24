package edu.wofford.sykesda.wocoapptest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(34.958486, -81.934407);
        final Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Wofford College"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMinZoomPreference(15);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        boolean alreadyMoved = FALSE;

        Button findLocation = (Button) findViewById(R.id.findLocation);
        final Spinner buildingSelection = (Spinner) findViewById(R.id.findBuildingSpinner);


        final HashMap<String, LatLng> myMap = new HashMap<>();

        myMap.put("Marsh Residence Hall", new LatLng(34.957825, -81.932325));
        myMap.put("Greene Residence Hall", new LatLng(34.958399, -81.933004));
        myMap.put("Carlisle Residence Hall", new LatLng(34.958080, -81.933176));
        myMap.put("Shipp Residence Hall", new LatLng(34.959552, -81.936192));
        myMap.put("DuPre Residence Hall", new LatLng(34.959877, -81.934692));
        myMap.put("Wightman Residence Hall", new LatLng(34.959554, -81.938918));
        myMap.put("Lesesne Residence Hall", new LatLng(34.959739, -81.937974));
        myMap.put("Phase V (MSBVC)", new LatLng(34.960157, -81.938076));
        myMap.put("The Village", new LatLng(34.961436, -81.938103));

        // Now we update the map location
        findLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMinZoomPreference(19);
                //mMap.setMinZoomPreference(15);

                String selectedBuildingStr = buildingSelection.getSelectedItem().toString();
                LatLng selectedPosition = myMap.get(selectedBuildingStr);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(selectedPosition));

                myMarker.setPosition(selectedPosition);
                myMarker.setTitle(selectedBuildingStr);

                mMap.setMinZoomPreference(15);

            }
        });

    }
}
