package event.epihack.epihackdengue;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {
    Context context;
    Data data = new Data();
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    private GoogleMap mMap;

    LocationRequest
            mLocationRequest;

    GoogleApiClient
            mGoogleApiClient;

    private static final long
            INTERVAL = 1000 * 20;

    private static final long
            FASTEST_INTERVAL = 10 * 5;

    double
            latitude = 0.00,
            longitude = 0.00;

    Marker myCurrentLocationMarker;

    String userID = "";

    Circle[] circle = null;

    String[]
            hotspot_category_name,
            hotspot_name,
            hotspot_address,
            hotspot_city,
            hotspot_state,
            hotspot_lat,
            hotspot_lng,
            subscribed_area_id;

    ArrayList<Circle> sssss = new ArrayList<>();

    double[] radiusArray= new double[]{3000, 5000, 10000, 1000, 2500};

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOption();
            }
        });

        context = MapsActivity.this;

        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        SharedPreferences sp = getSharedPreferences("credentials", 0);
        userID += sp.getString("user_id","1");

        mGoogleApiClient.connect();
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
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setBuildingsEnabled(true);

        new GetHotspots(userID).execute();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new com.google.android.gms.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

               if(myCurrentLocationMarker != null){ // if the marker is already in there.
                   try{
                       LatLng currLocation = new LatLng(latitude, longitude);
                       myCurrentLocationMarker.setPosition(currLocation);
                       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currLocation, 10.0f));
                   }
                   catch (Exception e){
                       LatLng currLocation = new LatLng(latitude, longitude);
                       myCurrentLocationMarker = mMap.addMarker(new MarkerOptions().position(currLocation).title("My Location"));
                       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currLocation, 10.0f));
                   }
               }
               else{ //  if the marker is not in the map
                   LatLng currLocation = new LatLng(latitude, longitude);
                   myCurrentLocationMarker = mMap.addMarker(new MarkerOptions().position(currLocation).title("My Location"));
                   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currLocation, 10.0f));
               }
            }
        });
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    class GetHotspots extends AsyncTask<String, Void, String[][]> {
        String
                userID = "";

        ProgressDialog progressDialog;
        boolean internetAvailable;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Sending Data", "Please wait");
        }

        public GetHotspots(String userID){
            this.userID = userID;
        }

        @Override
        protected String[][] doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                String[][] result = dbOperations.getAllHotspots(userID);
                return result;
            }
            else{
                this.internetAvailable = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[][] result) {
            try{
                progressDialog.dismiss();
            }
            catch(Exception e){}

            if(result != null){
                try{
                   hotspot_category_name = result[0];
                    hotspot_name = result[1];
                    hotspot_address = result[2];
                    hotspot_city = result[3];
                    hotspot_state = result[4];
                    hotspot_lat = result[5];
                    hotspot_lng = result[6];
                    subscribed_area_id = result[7];

                   for(int k= 0; k < hotspot_name.length; k++){
                       String hpAddress = hotspot_address[k] + ", " + hotspot_city[k] + ", " + hotspot_state[k];

                       System.out.println("Size : " + hotspot_name.length);
                       sssss.add(mMap.addCircle(new CircleOptions()
                               .center(new LatLng(Double.parseDouble(hotspot_lat[k]), Double.parseDouble(hotspot_lng[k])))
                               .radius(radiusArray[k])
                               .strokeColor(Color.parseColor("#44ff0000"))
                               .fillColor(Color.parseColor("#440000ff"))));
                   }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else if(this.internetAvailable){
                Toast.makeText(context, "No Hotspots found", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        Intent i = new Intent(context, MainDashBoardActivity.class);
        startActivity(i);
        finish();
    }
}
