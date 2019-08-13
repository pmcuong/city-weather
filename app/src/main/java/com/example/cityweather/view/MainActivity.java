package com.example.cityweather.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.cityweather.R;
import com.example.cityweather.base.BaseActivity;
import com.example.cityweather.databinding.ActivityMainBinding;
import com.example.cityweather.model.AddressModel;
import com.example.cityweather.util.AppUtil;
import com.example.cityweather.util.LogUtil;
import com.example.cityweather.viewmodel.AddressViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity <ActivityMainBinding, AddressViewModel> {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    // lists for permissions
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    // integer for permissions results request
    private static final int ALL_PERMISSIONS_RESULT = 1011;
    private FusedLocationProviderClient fusedLocationClient;
    @SuppressLint("MissingPermission")
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Class getClassViewModel() {
        return AddressViewModel.class;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        LogUtil.d("onClick");
        switch (view.getId()){
            case R.id.ll_wiki:
                AppUtil.gotoWikiScreen(MainActivity.this, viewModel.getAddressData().getValue().getCityName());
                break;
            case R.id.ll_images:
                break;
            case R.id.ll_weather:
                startActivity(new Intent(this, WeatherActivity.class));
                break;
            case R.id.ll_maps:
                AppUtil.openMap(this, Double.parseDouble(viewModel.getAddressData().getValue().getLatitude()),
                        Double.parseDouble(viewModel.getAddressData().getValue().getLongtitude()));
                break;
        }
    }

    @Override
    public void initView() {
        // we add permissions we need to request location of the users
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionsToRequest = permissionsToRequest(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        viewModel.getAddressData().observe(this, new Observer<AddressModel>() {
            @Override
            public void onChanged(@Nullable AddressModel addressModel) {
                binding.layoutInfo.setAddressModel(addressModel);
            }
        });

        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.setAddressData(new AddressModel("Mountain","", 0, 0, 0 ));
                binding.swiperefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                LogUtil.e("onRequestPermissionsResult: "+permissionsToRequest.size());
                if (permissionsToRequest.size()>0) {
                    for (int i=0;i<permissionsToRequest.size();i++) {
                        if (!hasPermission(permissionsToRequest.get(i))) {
                            permissionsRejected.add(permissionsToRequest.get(i));
                        }else {
                            permissionsToRequest.remove(i);
                            i--;
                        }
                    }
                }

//                for (int i=0;i<permissionsToRequest.size();i++) {
                    LogUtil.e("onRequestPermissionsResult: "+permissionsToRequest.size());
//                }
                if (permissionsToRequest.size()==0){
                    getLocation();
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            new AlertDialog.Builder(MainActivity.this).
                                    setMessage("These permissions are mandatory to get your location. You need to allow them.").
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.
                                                        toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    }).setNegativeButton("Cancel", null).create().show();

                            return;
                        }
                    }
                } else {
                    getLocation();
                }

                break;
        }
    }

    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();
        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!checkPlayServices()) {
//            locationTv.setText("You need to install Google Play Services to use the App properly");
        }
        getLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
                finish();
            }

            return false;
        }

        return true;
    }

    @SuppressLint("StaticFieldLeak")
    void getCityName(final Context context, final double latitude, final double longtitude) {
        new AsyncTask<Void, Integer, List<Address>>() {
            @Override
            protected List<Address> doInBackground(Void... arg0) {
                Geocoder coder = new Geocoder(context, Locale.getDefault());
                List<Address> results = null;
                try {
                    results = coder.getFromLocation(latitude, longtitude, 1);
                    if (results.size() > 0) {
                        LogUtil.e("getCityName: "+results);
                    }
                    else {
                        // do your stuff
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return results;
            }

            @Override
            protected void onPostExecute(List<Address> results) {
                String cityName = results.get(0).getLocality();
                String geonameId = results.get(0).getPostalCode();
                LogUtil.e("getCityName: "+results.get(0).getLocality());
                viewModel.  setAddressData(new AddressModel(cityName, geonameId, latitude, longtitude, 0));
            }
        }.execute();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            Log.e("MainActivity", "onSuccess: " + location);
                            if (location != null) {
                                // Logic to handle location object
                                getCityName(MainActivity.this, location.getLatitude(), location.getLongitude());
                                viewModel.setAddressData(new AddressModel("", "", location.getLatitude(), location.getLongitude(), 0));
                            }
                        }
                    });
        }
    }

    private void updateCityInfo(AddressModel addressModel){
        if (binding!=null){
            binding.layoutInfo.strCity.setText(addressModel.getCityName());
            binding.layoutInfo.strGeonameId.setText(addressModel.getGeonameId());
            binding.layoutInfo.strLatitude.setText(addressModel.getLatitude());
            binding.layoutInfo.strLongtitude.setText(addressModel.getLongtitude());
            binding.layoutInfo.strPopulation.setText(addressModel.getPopulation());
        }
    }


}
