package event.epihack.epihackdengue;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class LoginActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Data data = new Data();
    DBOperations dbOperations= new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    String user_token = "";

    Context context;

    Button
            loginButton,
            registerButton;

    EditText
            userNameField,
            passwordField;

    LocationRequest
            mLocationRequest;

    GoogleApiClient
            mGoogleApiClient;

    private static final long
            INTERVAL = 1000 * 1;

    private static final long
            FASTEST_INTERVAL = 10 * 5;

    double
            latitude = 0.00,
            longitude = 0.00;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        context = LoginActivity.this;

        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        SharedPreferences sp = getSharedPreferences("credentials", 0);
        user_token += sp.getString("user_device_token", "unknown");

        mGoogleApiClient.connect();

        userNameField = (EditText) findViewById(R.id.userNameField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if(userName.isEmpty() || password.isEmpty()){
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();

                    if(userName.isEmpty()){
                        userNameField.setError("Please fill this field");
                    }
                    else{
                        userNameField.setError(null);
                    }

                    if(password.isEmpty()){
                        passwordField.setError("Please fill this field");
                    }
                    else{
                        passwordField.setError(null);
                    }
                }
                else {
                    new CheckCredentials(userName, password, user_token, String.valueOf(latitude), String.valueOf(longitude)).execute();
                }
            }
        });

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SignUpPageActivity.class);
                startActivity(i);
                finish();
            }
        });
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

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        System.exit(0);
    }


    class CheckCredentials extends AsyncTask<String, Void, String[]> {
        String userName = "", Password = "", token = "";
        ProgressDialog progressDialog;
        boolean internetAvailable;

        String
                latitude = "",
                longitude = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Checking Credentials", "Please wait");
        }

        public CheckCredentials(String userName, String Password, String token, String latitude, String longitude){
            this.userName = userName;
            this.Password = Password;
            this.token = token;

            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected String[] doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                String[] result = dbOperations.getLoginDetails(userName, Password, token, latitude, longitude);
                return result;
            }
            else{
                this.internetAvailable = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] result) {
            try{
                progressDialog.dismiss();
            }
            catch(Exception e){}

            if(result != null){
                try{
                    String user_id = result[0];
                        String user_role_id = result[1];
                    String user_first_name = result[2];
                    String user_last_name = result[3];
                    String user_email_address = result[4];
                    String user_gender = result[5];
                    String user_role_name = result[6];

                    SharedPreferences sp = getSharedPreferences("credentials", 0);
                    SharedPreferences.Editor spEditor = sp.edit();
                    spEditor.putBoolean("user_logged", true);
                    spEditor.putString("user_id", user_id);
                    spEditor.putString("user_role_id",user_role_id);

                    spEditor.putString("user_first_name",user_first_name);
                    spEditor.putString("user_last_name",user_last_name);

                    spEditor.putString("user_email_address",user_email_address);

                    spEditor.putString("user_gender",user_gender);
                    spEditor.putString("user_role_name",user_role_name);
                    spEditor.apply();

                            Intent i4 = new Intent(context, MainDashBoardActivity.class);
                            i4.putExtra("user_role_id",user_role_id);
                            i4.putExtra("user_first_name",user_first_name);
                            i4.putExtra("user_last_name",user_last_name);
                            i4.putExtra("user_email_address", user_email_address);
                            i4.putExtra("user_gender", user_gender);
                            i4.putExtra("user_role_name", user_role_name);
                            startActivity(i4);
                            finish();
                }
                catch(Exception e){
                    Toast.makeText(context, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }
            else if(this.internetAvailable){
                Toast.makeText(context, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
