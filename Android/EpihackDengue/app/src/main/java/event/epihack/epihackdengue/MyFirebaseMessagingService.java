package event.epihack.epihackdengue;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sumudu on 6/22/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String value1 = remoteMessage.getData().get("value1");
        String value2 = remoteMessage.getData().get("value2");
        String value3 = remoteMessage.getData().get("value3");

        Intent intent = new Intent(this, MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bell_outline) // change this line
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(value1);
       // bigTextStyle.bigText(value2 + '\n' + value3 + '\n' + value4 + '\n' + value5 + '\n' + value6 +  '\n' + value7 + '\n' + value8 + '\n' + value9 + '\n' + value10);


        bigTextStyle.bigText(value2 + '\n' + value3);

        notificationBuilder.setStyle(bigTextStyle);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onDestroy() {
        Intent i = new Intent(MyFirebaseMessagingService.this, MyFirebaseMessagingService.class);
        startService(i);
    }

    public String getTodayDateWithMonthAndYearDay() {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
}
