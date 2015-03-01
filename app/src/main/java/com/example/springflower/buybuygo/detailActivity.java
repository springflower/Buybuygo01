package com.example.springflower.buybuygo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class detailActivity extends FragmentActivity {
    private TextView tvtitle;
    private TextView tvcontent;
    private TextView tvuserphone;
    private RecyclerView mRecyclerView;
    private MapView mapView;
    private Marker markerMe;
    private GoogleMap mMap;
    private double dLat = 0;
    private double dLng = 0;
    public Context context;

    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        findViews();
        setUpMapIfNeeded();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(dLat, dLng)).title("交貨點")).showInfoWindow();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(dLat, dLng), 16);
        mMap.animateCamera(cameraUpdate);
    }
//
//    private void showMarkerMe(double lat, double lng){
//        if (markerMe != null) {
//            markerMe.remove();
//        }
//
//        MarkerOptions markerOpt = new MarkerOptions();
//        markerOpt.position(new LatLng(lat, lng));
//        markerOpt.title("我在這裡");
//        markerMe = map.addMarker(markerOpt);
//
//
//        //Toast.makeText(this, "lat:" + lat + ",lng:" + lng, Toast.LENGTH_SHORT).show();
//    }
//    private Location getLocation(Context context) {
//        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        if (location == null)
//            location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//        return location;
//    }
    private void findViews(){
        tvtitle = (TextView)findViewById(R.id.info_text);
        tvcontent = (TextView)findViewById(R.id.info_text2);
        tvuserphone = (TextView)findViewById(R.id.info_text3);
        //mapView= (MapView)findViewById(R.id.map2);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        //Bundle bundle = this.getIntent().getExtras();
        title = bundle.getString("title");
        String content = bundle.getString("content");
        String userphone = bundle.getString("userphone");
        dLat=bundle.getDouble("dlat");
        dLng=bundle.getDouble("dlng");
        tvtitle.setText(title);
        tvcontent.setText(content);
        tvuserphone.setText(userphone);


        //context = getApplicationContext();
        //Location mLocation = getLocation(this.context);
        //dLat = mLocation.getLatitude();//取得現在所在的緯度
        //dLng = mLocation.getLongitude();//取得現在所在的經度
//        try {
//            MapsInitializer.initialize(this);
//        } catch (Exception e) {
//            Log.e("Address Map", "Could not initialize google play", e);
//        }
//
//        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) )
//        {
//            case ConnectionResult.SUCCESS:
//                Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
//                //mapView = (MapView) v.findViewById(R.id.mapFrag);
//                // Gets to GoogleMap from the MapView and does initialization stuff
//                if(mapView!=null)
//                {
//                    map = mapView.getMap();
//                    map.getUiSettings().setMyLocationButtonEnabled(true);
//                    map.setMyLocationEnabled(true);
//                    showMarkerMe(dLat, dLng);
//                    //showMarkerMe2(cursor);
//                    //showMarkerMe(dLat+0.555, dLng+0.597);
//                    //map.isMyLocationEnabled();
//                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(24.5575137, 120.8140948), 15);
//                    map.animateCamera(cameraUpdate);
//
//                    //map.setMyLocationEnabled(true);
//                }
//                break;
//            case ConnectionResult.SERVICE_MISSING:
//                Toast.makeText(this, "SERVICE MISSING", Toast.LENGTH_SHORT).show();
//                break;
//            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
//                Toast.makeText(this, "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
//                break;
//            default: Toast.makeText(this, GooglePlayServicesUtil.isGooglePlayServicesAvailable(this), Toast.LENGTH_SHORT).show();
//        }



    }
}
