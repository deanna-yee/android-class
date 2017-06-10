package com.yee.googlemaps;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class definition for the main activity
* Assignment #: 8
* Date: 5/11/17
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Latitude and Longitude borders for the map
    private LatLngBounds SOCAL = new LatLngBounds(new LatLng(33.6, -118), new LatLng(33.9, -117.75));
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*
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

        if (mMap != null) {
            //set map type to normal map
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            //set zoom controls
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);

            //center map and set zoom
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SOCAL.getCenter(), 11));
            addMarkers();
        }

    }

    //add markers with infoWindows to the map
    public void addMarkers(){
        LatLng UCI = new LatLng(33.640495, -117.842745);
        mMap.addMarker(new MarkerOptions().position(UCI)
                .title("Aldrich Park")
                .snippet("University of California Irvine"));

        LatLng DISNEYLAND = new LatLng(33.812092, -117.918974);
        mMap.addMarker(new MarkerOptions().position(DISNEYLAND)
                .title("Disneyland")
                .snippet("1313 Disneyland Dr."));

        LatLng CHAFORTEA = new LatLng(33.650708, -117.838947);
        mMap.addMarker(new MarkerOptions().position(CHAFORTEA)
                .title("Cha For Tea")
                .snippet("4187 Campus Dr. m173"));

        LatLng CLASS302 = new LatLng(33.663671, -117.82586);
        mMap.addMarker(new MarkerOptions().position(CLASS302)
                .title("Class 302")
                .snippet("18090 Culver Dr"));

        LatLng BAKERY_85DEGREE = new LatLng(33.688055, -117.834128);
        mMap.addMarker(new MarkerOptions().position(BAKERY_85DEGREE)
                .title("85 Degree Bakery")
                .snippet("2700 Alton Pkwy #123"));
    }
}
