package event.epihack.epihackdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Sumudu on 11/9/2017.
 */

public class CustomMenuGridViewAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    String[] menuItemName;
    int[] menuItemIcon;

    public CustomMenuGridViewAdapter(
            Context context,
            Activity activity,

            String[] menuItemName,
            int[] menuItemIcon){

        this.context = context;
        this.activity = activity;

        this.menuItemName = menuItemName;
        this.menuItemIcon = menuItemIcon;
    }

    @Override
    public int getCount() {
        return menuItemName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gvContent = inflater.inflate(R.layout.menu_grid_item_layout, null);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        gvContent.startAnimation(animation);

        final CardView menuItem = (CardView) gvContent.findViewById(R.id.menuItem);
        menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0 :
                        Intent i1 = new Intent(context, ViewEmergencyNumbersActivity.class);
                        context.startActivity(i1);
                        activity.finish();
                        break;

                    case 1 :
                        Intent i2 = new Intent(context, MapsActivity.class);
                        context.startActivity(i2);
                        activity.finish();
                        break;
                }
            }
        });

        final TextView itemName = (TextView) gvContent.findViewById(R.id.itemName);
        final ImageView itemImage = (ImageView) gvContent.findViewById(R.id.itemImage);

        Picasso
                .with(context)
                .load(menuItemIcon[position])
                .into(itemImage);

        itemName.setText(menuItemName[position]);
        return gvContent;
    }
}
