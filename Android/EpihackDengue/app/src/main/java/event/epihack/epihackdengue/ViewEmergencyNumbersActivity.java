package event.epihack.epihackdengue;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class ViewEmergencyNumbersActivity extends Activity {
    Context context;
    Data data = new Data();
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    SwipeRefreshLayout swiper;
    ListView emergencyNumberListView;

    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_emergency_numbers_layout);
        context = ViewEmergencyNumbersActivity.this;

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOption();
            }
        });

        emergencyNumberListView = (ListView) findViewById(R.id.emergencyNumberListView);

        swiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        swiper.setColorSchemeColors(data.getSWIPER_REFRESH_COLORS());
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiper.setRefreshing(true);
                new GetEmergencyNumbers().execute();
            }
        });
        new GetEmergencyNumbers().execute();
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

    class GetEmergencyNumbers extends AsyncTask<String, Void, String[][]> {
        ProgressDialog progressDialog;
        boolean internetAvailable;


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Loading data", "Please wait");
        }

        @Override
        protected String[][] doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                String[][] result = dbOperations.getAllEmergencyNumbers();
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
                swiper.setRefreshing(false);
            }
            catch(Exception e){}

            if(result != null){
                try{
                    String[] emergency_number_id = result[0];
                    String[] emergency_number_name = result[1];
                    String[] emergency_number_address = result[2];
                    String[] emergency_number_contact_number1 = result[3];
                    String[] emergency_number_contact_number2 = result[4];
                    String[] emergency_number_contact_number3 = result[5];

                    CustomEmergencyNumberAdapter customEmergencyNumberAdapter = new CustomEmergencyNumberAdapter(
                            context,
                            ViewEmergencyNumbersActivity.this,

                            emergency_number_id,
                            emergency_number_name,
                            emergency_number_address,
                            emergency_number_contact_number1,
                            emergency_number_contact_number2,
                            emergency_number_contact_number3
                    );

                    customEmergencyNumberAdapter.notifyDataSetChanged();
                    emergencyNumberListView.setAdapter(customEmergencyNumberAdapter);

                }
                catch(Exception e){
                    Toast.makeText(context, "No emergency numbers found", Toast.LENGTH_SHORT).show();
                }
            }
            else if(this.internetAvailable){
                Toast.makeText(context, "No emergency numbers found", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
