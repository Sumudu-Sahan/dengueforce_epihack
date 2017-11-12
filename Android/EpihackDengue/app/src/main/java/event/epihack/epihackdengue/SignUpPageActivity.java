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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class SignUpPageActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Data data = new Data();
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    EditText
            firstNameField,
            lastNameField,

            userNameField,
            passwordField,

            emailAddressField,

            addressField,
            cityField,
            stateField,

            contactNumberField,
            mobileNumberField;

    Context context;

    LinearLayout submitButton;

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

    String user_token = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        context = SignUpPageActivity.this;

        SharedPreferences sp = getSharedPreferences("credentials", 0);
        user_token += sp.getString("user_device_token", "");

        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);

        userNameField = (EditText) findViewById(R.id.userNameField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        emailAddressField = (EditText) findViewById(R.id.emailAddressField);

        addressField = (EditText) findViewById(R.id.addressField);
        cityField = (EditText) findViewById(R.id.cityField);
        stateField = (EditText) findViewById(R.id.stateField);

        contactNumberField = (EditText) findViewById(R.id.contactNumberField);
        mobileNumberField = (EditText) findViewById(R.id.mobileNumberField);

        submitButton = (LinearLayout) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameField.getText().toString().trim();
                String lastName = lastNameField.getText().toString().trim();

                String userName = userNameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                String email = emailAddressField.getText().toString().trim();

                String address = addressField.getText().toString().trim();
                String city = cityField.getText().toString().trim();
                String state = stateField.getText().toString().trim();

                String contactNumber = contactNumberField.getText().toString().trim();
                String mobileNumber = mobileNumberField.getText().toString().trim();

                if(firstName.isEmpty() ||
                        lastName.isEmpty() ||
                        userName.isEmpty() ||
                        password.isEmpty() ||

                        email.isEmpty() ||

                        address.isEmpty() ||
                        city.isEmpty() ||
                        state.isEmpty() ||

                        contactNumber.isEmpty() ||
                        mobileNumber.isEmpty()){
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmail(email)){
                    Toast.makeText(context, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
                else{
                    new SigningUp(
                            firstName,
                            lastName,
                            userName,
                            password,
                            email,
                            address,
                            city,
                            state,
                            contactNumber,
                            mobileNumber,
                            user_token,
                            String.valueOf(latitude),
                            String.valueOf(longitude)).execute();
                }
            }
        });
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        Intent i = new Intent(context, LoginActivity.class);
        startActivity(i);
        finish();
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

    class SigningUp extends AsyncTask<String, Void, Boolean> {

        String
                firstName = "",
                lastName = "",
                userName = "",
                password = "",
                email = "",
                address = "",
                city = "",
                state = "",
                contactNumber = "",
                mobileNumber = "";

        String token = "";

        ProgressDialog progressDialog;
        boolean internetAvailable;

        String
                latitude = "",
                longitude = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Sending Data", "Please wait");
        }

        public SigningUp(
                String firstName,
                String lastName,

                String userName,
                String password,

                String email,
                String address,
                String city,
                String state,

                String contactNumber,
                String mobileNumber,

                String token,
                String latitude,
                String longitude){

            this.firstName = firstName;
            this.lastName = lastName;

            this.userName = userName;
            this.password = password;

            this.email = email;
            this.address = address;
            this.city = city;
            this.state = state;

            this.contactNumber = contactNumber;
            this.mobileNumber = mobileNumber;

            this.token = token;

            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                boolean result = dbOperations.signingUp(
                        firstName,
                        lastName,

                        userName,
                        password,

                        email,
                        address,
                        city,
                        state,

                        contactNumber,
                        mobileNumber,

                        token,
                        latitude,
                        longitude);

                return result;
            }
            else{
                this.internetAvailable = false;
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            try{
                progressDialog.dismiss();
            }
            catch(Exception e){}

            if(result){
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, LoginActivity.class);
                startActivity(i);
                finish();
            }
            else if(this.internetAvailable){
                Toast.makeText(context, "Unable to add the login. Please try again", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
