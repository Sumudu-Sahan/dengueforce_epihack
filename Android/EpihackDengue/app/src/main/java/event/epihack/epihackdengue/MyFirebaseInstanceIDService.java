package event.epihack.epihackdengue;

/**
 * Created by Sumudu on 6/22/2016.
 */
import android.content.SharedPreferences;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


//Class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Displaying token on logcat

        SharedPreferences sp = getSharedPreferences("credentials", 0);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString("user_device_token",refreshedToken);
        spEditor.apply();

        System.out.println("Token is: " + refreshedToken);
    }

    @Override
    public void onDestroy() {
    }
}