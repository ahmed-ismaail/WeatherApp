package com.example.locationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements
        LocationsAdapter.OnLocationItemClick, OnMapReadyCallback, LocationListener {

    private static final String TAG = "MainActivity";
    private Location location;
    private LocationManager locationManager;
    private static final String FINE_ACCESS_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_ACCESS_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_REQUEST_CODE = 101;
    private ArrayList<ResponseLocation> responseLocationArrayList;
    RecyclerView recyclerView;
    LocationsAdapter locationsAdapter;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, FINE_ACCESS_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this, COARSE_ACCESS_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {FINE_ACCESS_LOCATION, COARSE_ACCESS_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, LOCATION_REQUEST_CODE);
        }
        updateLastKnownLocation();

        recyclerView = findViewById(R.id.recycler);
        responseLocationArrayList = new ArrayList<>();
        locationsAdapter = new LocationsAdapter(responseLocationArrayList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(locationsAdapter);

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        callMocky(map);
    }

    private void callMocky(GoogleMap googleMap) {
        //String url = "https://run.mocky.io/v3/b41be4b5-8d78-4870-8321-3089535f2a69";



        Observable observable = RetrofitClient.getInstance().retrieveLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer<List<ResponseLocation>> observer = new Observer<List<ResponseLocation>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(List<ResponseLocation> responseLocations) {
                 locationsAdapter.updateAdapter((ArrayList<ResponseLocation>) responseLocations);
                for (int i = 0 ; i<responseLocations.size();i++) {
                    ResponseLocation location = responseLocations.get(i);
                    LatLng latLng = new LatLng(Double.parseDouble(location.getLatitude()),
                            Double.parseDouble(location.getLongitude()));
                    map.addMarker(new MarkerOptions().position(latLng).title("location.getName()"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

                //enqueue(new Callback<List<ResponseLocation>>() {
        //            @Override
        //            public void onResponse(Call<List<ResponseLocation>> call, Response<List<ResponseLocation>> response) {
        //              //  Gson gson = new Gson();
        //                String s = response.body().toString();
        //
        //                responseLocations = (ArrayList<ResponseLocation>) response.body();
        //
        //                locationsAdapter.updateAdapter(responseLocations);
        //
        //                for (int i = 0 ; i<responseLocations.size();i++) {
        //                    ResponseLocation location = responseLocations.get(i);
        //                    LatLng latLng = new LatLng(Double.parseDouble(location.getLatitude()),
        //                            Double.parseDouble(location.getLongitude()));
        //                    map.addMarker(new MarkerOptions().position(latLng).title("location.getName()"));
        //                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //                }
        //
        //                Log.d(TAG, "onResponse: response " + s);
        //            }
        //
        //            @Override
        //            public void onFailure(Call<List<ResponseLocation>> call, Throwable t) {
        //            }
        //        });
    }

    @Override
    public void onItemClickListener(int position) {
        Toast.makeText(this, responseLocationArrayList.get(position).getName(),
                Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                10, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && ActivityCompat.checkSelfPermission(
                    this, FINE_ACCESS_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, COARSE_ACCESS_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "you didn't approve permissions",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "you are all set", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.location = location;
    }

    @SuppressLint("MissingPermission")
    private void updateLastKnownLocation() {
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }


//    private boolean checkGooglePlayServicesAvailable() {
//        int status = GoogleApiAvailability.getInstance().
//                isGooglePlayServicesAvailable(this);
//
//        if (status == ConnectionResult.SUCCESS) {
//            return true;
//        }
//        Log.e("TAG", "Google Play Services not Available: " + GoogleApiAvailability.
//                getInstance().getErrorString(status));
//        if (GoogleApiAvailability.getInstance().isUserResolvableError(status)) {
//            Dialog errorDialog = GoogleApiAvailability.getInstance().getErrorDialog(this,
//                    status, 1);
//            if (errorDialog != null) {
//                errorDialog.show();
//            }
//        }
//        return false;
//    }
}