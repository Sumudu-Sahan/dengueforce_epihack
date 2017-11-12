package event.epihack.epihackdengue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Sumudu on 2/17/2017.
 */

public class CustomNotificationAdapter extends BaseAdapter {
    Context
            context;

    String[]
            mainValueList,
            subValue1List,
            subValue2List,
            subValue3List,
            subValue4List;

    public CustomNotificationAdapter(
            Context contexts,
            String[] mainValueList,
            String[] subValue1List,
            String[] subValue2List,
            String[] subValue3List,
            String[] subValue4List){

        this.context = contexts;

        this.mainValueList = mainValueList;
        this.subValue1List = subValue1List;
        this.subValue2List = subValue2List;
        this.subValue3List = subValue3List;
        this.subValue4List = subValue4List;
    }

    @Override
    public int getCount() {
        return mainValueList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gvContent = inflater.inflate(R.layout.notification_item_layout, null);

       /* final TextView mainValueDisplay = (TextView) gvContent.findViewById(R.id.mainValueDisplay);
        final TextView subValue1Display = (TextView) gvContent.findViewById(R.id.subValue1Display);
        final TextView subValue2Display = (TextView) gvContent.findViewById(R.id.subValue2Display);
        final TextView subValue3Display = (TextView) gvContent.findViewById(R.id.subValue3Display);
        final TextView subValue4Display = (TextView) gvContent.findViewById(R.id.subValue4Display);

        mainValueDisplay.setText(mainValueList[position]);
        subValue1Display.setText(subValue1List[position]);
        subValue2Display.setText(subValue2List[position]);
        subValue3Display.setText(subValue3List[position]);
        subValue4Display.setText(subValue4List[position]);*/

        return gvContent;
    }
}
