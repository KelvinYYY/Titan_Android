package com.example.kelvin.laiofferproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback
{
    public final static String EXTRA_LATLNG = "EXTRA_LATLNG";
    private Intent intent;
    private LatLng toMark;
    private int number = 8;
    private MapFragment mapFragment;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            toMark = bundle.getParcelable(EXTRA_LATLNG);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.restaurant_map);
        mapFragment.getMapAsync(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("GOOGLEMAP_ZOOM");
        mBroadcastReceiver = new ZoomMap();
        this.registerReceiver(mBroadcastReceiver, filter);

    }
    @Override
    public void onMapReady(GoogleMap map) {
        if (toMark != null) {
            map.addMarker(new MarkerOptions().position(toMark).title("Marker"));
            map.moveCamera(CameraUpdateFactory.newLatLng(toMark));
            map.animateCamera(CameraUpdateFactory.zoomTo(number), 2000, null);
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(mBroadcastReceiver);
    }

    class ZoomMap extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                number = Integer.parseInt(extras.getString("ZOOM"));
                mapFragment.getMapAsync(MapActivity.this);
            }
        }
    }


}
