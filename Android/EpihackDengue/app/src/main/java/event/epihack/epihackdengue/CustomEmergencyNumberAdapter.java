package event.epihack.epihackdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class CustomEmergencyNumberAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    String[]
            emergency_number_id,
            emergency_number_name,
            emergency_number_address,
            emergency_number_contact_number1,
            emergency_number_contact_number2,
            emergency_number_contact_number3;

    public CustomEmergencyNumberAdapter(
            Context context,
            Activity activity,

            String[] emergency_number_id,
            String[] emergency_number_name,
            String[] emergency_number_address,
            String[] emergency_number_contact_number1,
            String[] emergency_number_contact_number2,
            String[] emergency_number_contact_number3){

        this.context = context;
        this.activity = activity;

        this.emergency_number_id = emergency_number_id;
        this.emergency_number_name= emergency_number_name;
        this.emergency_number_address = emergency_number_address;
        this.emergency_number_contact_number1 = emergency_number_contact_number1;
        this.emergency_number_contact_number2 = emergency_number_contact_number2;
        this.emergency_number_contact_number3 = emergency_number_contact_number3;
    }


    @Override
    public int getCount() {
        return emergency_number_id.length;
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
        View gvContent = inflater.inflate(R.layout.emergency_number_item_layout, null);

        final TextView numberNameField = (TextView) gvContent.findViewById(R.id.numberNameField);
        final TextView numberAddressField = (TextView) gvContent.findViewById(R.id.numberAddressField);

        final TextView number1Field = (TextView) gvContent.findViewById(R.id.number1Field);
        final TextView number2Field = (TextView) gvContent.findViewById(R.id.number2Field);
        final TextView number3Field = (TextView) gvContent.findViewById(R.id.number3Field);

        number1Field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callingNumber= "tel:" + emergency_number_contact_number1[position];
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(callingNumber));
                context.startActivity(intent);
            }
        });

        number2Field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callingNumber= "tel:" + emergency_number_contact_number2[position];
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(callingNumber));
                context.startActivity(intent);
            }
        });

        number3Field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callingNumber= "tel:" + emergency_number_contact_number3[position];
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(callingNumber));
                context.startActivity(intent);
            }
        });

        numberNameField.setText(emergency_number_name[position]);
        numberAddressField.setText(emergency_number_address[position]);

        number1Field.setText(emergency_number_contact_number1[position]);
        number2Field.setText(emergency_number_contact_number2[position]);
        number3Field.setText(emergency_number_contact_number3[position]);

        return gvContent;
    }
}
