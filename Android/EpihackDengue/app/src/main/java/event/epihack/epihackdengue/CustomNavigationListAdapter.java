package event.epihack.epihackdengue;

/**
 * Created by Sumudu on 8/25/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomNavigationListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    Context context;
    Activity activity;

    String[] itemNameArray;
    int[] itemIconArray;

    public CustomNavigationListAdapter(
            final Context context,
            Activity activity,

            String[] itemNameArray,
            int[] itemIconArray) {
        super();
        this.context = context;
        this.itemNameArray = itemNameArray;
        this.itemIconArray = itemIconArray;
        this.activity = activity;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemNameArray.length;
    }

    @Override
    public Object getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView itemName;
        ImageView itemImage;

        CardView navigationItem;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.navigation_item, null, false);
            holder.itemName = (TextView) convertView.findViewById(R.id.itemNameTextView);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.itemImageView);
            holder.navigationItem = (CardView) convertView.findViewById(R.id.navigationItem);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        holder.itemName.setText(itemNameArray[position]);
        holder.itemImage.setImageResource(itemIconArray[position]);
        holder.navigationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 1:
                        break;

                    case 0:
                        new AlertDialog.Builder(context)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Confirm")
                                .setMessage("Do you want to logout?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        SharedPreferences sp =context.getSharedPreferences("credentials", 0);
                                        SharedPreferences.Editor spEditor = sp.edit();
                                        spEditor.putBoolean("user_logged", false);
                                        spEditor.putString("user_role_id","");

                                        spEditor.putString("user_first_name","");
                                        spEditor.putString("user_last_name","");

                                        spEditor.putString("user_email_address","");

                                        spEditor.putString("user_gender","");
                                        spEditor.putString("user_role_name","");
                                        spEditor.apply();

                                        Intent i9 = new Intent(activity, LoginActivity.class);
                                        activity.startActivity(i9);
                                        activity.finish();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                        break;

                    case 2:

                        break;
                }
            }
        });
        return convertView;
    }
}