package sg.edu.rp.c347.practicalquiz9;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SecondActivity extends AppCompatActivity {

    private GoogleMap map;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //btn = findViewById(R.id.button);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                Intent intent = getIntent();
                String coordinates = intent.getStringExtra("c");

                String[] c = coordinates.split(",");

                double lat = Double.parseDouble(c [0]);
                double lng = Double.parseDouble(c [1]);

                LatLng newC = new LatLng(lat, lng);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(newC, 14));

                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(newC)
                        .title("Your location is here")
                        .snippet(coordinates)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
            }
        });

        if (map != null){
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/


    }
}
