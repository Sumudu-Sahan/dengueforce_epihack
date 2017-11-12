package event.epihack.epihackdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class MainDashBoardActivity extends Activity {
    Data data = new Data();
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    Context context;

    DrawerLayout drawer_layout; // drawer_layout
    NavigationView whatYouWantInLeftDrawer; // whatYouWantInLeftDrawer

    String
            user_role_id = "",
            user_first_name = "",
            user_last_name = "",
            user_email_address = "",
            user_gender = "",
            user_role_name = "";

    ImageView loggedUserImage, navigationButton;
    TextView loggedUsernameDisplay, loggedUserTypeDisplay;
    ListView navigationListView;

    GridView mainMenuGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard_layout);
        context = MainDashBoardActivity.this;

        mainMenuGridView = (GridView) findViewById(R.id.mainMenuGridView);

        loggedUsernameDisplay = (TextView) findViewById(R.id.loggedUsernameDisplay);
        loggedUserTypeDisplay = (TextView) findViewById(R.id.loggedUserTypeDisplay);

        navigationListView = (ListView) findViewById(R.id.navigationListView);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        whatYouWantInLeftDrawer = (NavigationView) findViewById(R.id.whatYouWantInLeftDrawer);

        navigationButton = (ImageView) findViewById(R.id.navigationButton);
        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(whatYouWantInLeftDrawer);
            }
        });

        Intent i = getIntent();
        if(i != null){
            user_role_id += i.getStringExtra("user_role_id");
            user_first_name += i.getStringExtra("user_first_name");
            user_last_name += i.getStringExtra("user_last_name");
            user_email_address += i.getStringExtra("user_email_address");
            user_gender += i.getStringExtra("user_gender");
            user_role_name += i.getStringExtra("user_role_name");

           // loggedUsernameDisplay.setText(user_first_name + " " + user_last_name);
           // loggedUserTypeDisplay.setText(user_role_name);
        }

        CustomMenuGridViewAdapter customMenuGridViewAdapter
                = new CustomMenuGridViewAdapter(
                        context,
                MainDashBoardActivity.this,
                data.getMenuItemNames(),
                data.getMenuItemIcons());

        customMenuGridViewAdapter.notifyDataSetChanged();
        mainMenuGridView.setAdapter(customMenuGridViewAdapter);

        CustomNavigationListAdapter customNavigationListAdapter
                = new CustomNavigationListAdapter(
                context,
                MainDashBoardActivity.this,
                data.getNavigationItemNames(),
                data.getNavigationItemIcons());

        customNavigationListAdapter.notifyDataSetChanged();
        navigationListView.setAdapter(customNavigationListAdapter);
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        System.exit(0);
    }
}
