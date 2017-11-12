package event.epihack.epihackdengue;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sumudu on 3/30/2016.
 */
public class NetworkStatChecker {

    static Data d = new Data();
    public Boolean isConnected(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()){
            try{
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                URL url = new URL(d.getSERVRE_API_PATH_ROOT().trim() + "ping.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                   return true;
                   // conn.disconnect();
                }
                else{
                    Toast.makeText(context, String.valueOf(conn.getResponseCode()), Toast.LENGTH_LONG).show();
                  return false;
                    //conn.disconnect();
                }
            } catch (Exception e) {
//                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                //System.out.println("Except :" + String.valueOf(e));
                return false;
            }
        }
        else {
            return false;
        }
    }
    public Boolean isWifiConnected(Context c){
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI);
    }

    public Boolean isEthernetConnected(Context c){
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_ETHERNET);
    }
}
