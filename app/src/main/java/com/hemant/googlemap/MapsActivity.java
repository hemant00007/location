package com.hemant.googlemap;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Geocoder geocoder;
    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney and move the camera
//       LatLng latLng = new LatLng(27.1751,78.0421);
//       MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Taj Mahal").snippet("Wonder of the World");
//       mMap.addMarker(markerOptions);
//       CameraUpdate cameraUpdateFactory = CameraUpdateFactory.newLatLngZoom(latLng,16);
//       mMap.animateCamera(cameraUpdateFactory);

        try {
            List<Address> addresses = geocoder.getFromLocationName("patna",1);

            if (addresses.size()>0){
                Address address = addresses.get(0);
                //   Log.d(TAG, "onMapReady: "+address.toString());
                LatLng patna =new LatLng(address.getLatitude(),address.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(patna)
                        .title(address.getLocality());
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(patna,15));
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
