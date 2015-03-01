package com.example.springflower.buybuygo;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {
    private double thislat;
    private double thislng;
    private GoogleMap mMap;
    private Marker markerMe;
    public Context context;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        findSomething();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void findSomething(){
        button = (Button)findViewById(R.id.button_mapActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putDouble("backlat", Math.round(thislat * 1000000.0) / 1000000.0);
                bundle.putDouble("backlng", Math.round(thislng * 1000000.0) / 1000000.0);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        thislat = bundle.getDouble("lat");
        thislng = bundle.getDouble("lng");
        setUpMapIfNeeded();
        context = getApplicationContext();
        Location mLocation = getLocation(this.context);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);
        showMarkerMe(thislat, thislng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(thislat, thislng), 16);
        mMap.moveCamera(cameraUpdate);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                thislat = latLng.latitude;
                thislng = latLng.longitude;
                showMarkerMe( thislat, thislng );
            }
        });

    }

    private Location getLocation(Context context) {
        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null)
            location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        return location;
    }
    private void showMarkerMe(double lat, double lng){
        if (markerMe != null) {
            markerMe.remove();
        }
        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(new LatLng(lat, lng));
        markerOpt.title("我在這裡");
        markerMe = mMap.addMarker(markerOpt);
    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
