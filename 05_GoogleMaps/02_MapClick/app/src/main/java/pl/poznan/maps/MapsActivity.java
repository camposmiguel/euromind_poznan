package pl.poznan.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private LatLng lastLocation = null;
    private int counter = 1;
    private double counterMeters = 0;

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
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerDragListener(this);

        // Add a marker in Zespol Szkol and move the camera
        LatLng poznanLocation = new LatLng(52.4252509,16.9436096);
        mMap.addMarker(new MarkerOptions()
                .position(poznanLocation)
                .title("Zespół Szkół Łączności im. Mikołaja Kopernika")
                .snippet("Poznan, Poland")
                .draggable(true)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(poznanLocation,17));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Marker markerNew = mMap.addMarker(new MarkerOptions()
            .position(latLng)
            .title("Maker " + counter)
            .draggable(true)
        );



        counter++;

        if(lastLocation!=null) {
            // Instantiates a new Polyline object and adds points to define a rectangle
            PolylineOptions rectOptions = new PolylineOptions()
                    .add(latLng)
                    .add(lastLocation)
                    .color(Color.GREEN);

            // Get back the mutable Polyline
            Polyline polyline = mMap.addPolyline(rectOptions);

            counterMeters+=SphericalUtil.computeDistanceBetween(lastLocation,latLng);
            Toast.makeText(MapsActivity.this, "Distance: "+counterMeters+" meters", Toast.LENGTH_SHORT).show();
        }

        lastLocation = latLng;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        LatLng positionMarker = marker.getPosition();
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        LatLng positionMarker = marker.getPosition();
        Log.i("New location","New location: "
                +positionMarker.latitude+","+positionMarker.longitude);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        marker.showInfoWindow();
    }
}
