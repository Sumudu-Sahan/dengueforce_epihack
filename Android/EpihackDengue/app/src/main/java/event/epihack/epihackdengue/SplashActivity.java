package event.epihack.epihackdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class SplashActivity extends Activity {
    Context context;
    NetworkStatChecker n = new NetworkStatChecker();
    Data data = new Data();
    DBOperations dbOperations = new DBOperations();

    ImageView splashImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        context = SplashActivity.this;

        splashImageView = (ImageView) findViewById(R.id.splashImageView);

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.blink);
        splashImageView.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new CheckCacheMemory().execute();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        System.exit(0);
    }

    class CheckCacheMemory extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... urls) {
            SharedPreferences sp = getSharedPreferences("credentials", 0);
            return sp.getBoolean("user_logged", false);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                SharedPreferences sp = getSharedPreferences("credentials", 0);

                Intent i = new Intent(context, MainDashBoardActivity.class);
                i.putExtra("user_role_id", sp.getString("user_role_id", ""));
                i.putExtra("user_first_name", sp.getString("user_first_name", ""));
                i.putExtra("user_last_name", sp.getString("user_last_name", ""));
                i.putExtra("user_email_address",  sp.getString("user_email_address", ""));
                i.putExtra("user_gender",  sp.getString("user_gender", ""));
                i.putExtra("user_role_name",  sp.getString("user_role_name", ""));
                startActivity(i);
                finish();
            }
            else{
                Intent i = new Intent(context, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
}
