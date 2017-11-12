package event.epihack.epihackdengue;

import android.graphics.Color;
import android.os.Environment;

import java.io.File;

/**
 * Created by Sumudu on 11/8/2017.
 */

public class Data {
    private final String SERVRE_API_PATH_ROOT= "http://10.194.208.69/epihack/PHP/";
    private final String SERVER_IMAGE_ROOT_PATH = "http://10.194.208.69/epihack/img/";
    private File STORAGE_PATH = new File(Environment.getExternalStorageDirectory()+"/.epihack");

    private final int[] SWIPER_REFRESH_COLORS = new int[]{Color.parseColor("#aa0000"), Color.parseColor("#0000aa"), Color.parseColor("#00aa00")}; // Red, Blue, Green Order


    private String[] menuItemNames = new String[]{"Emergency Numbers", "View Impact Hotspots"};
    private int[] menuItemIcons = new int[]{R.drawable.phone, R.drawable.map_marker_radius};

    private String[] navigationItemNames = new String[]{"Logout"};
    private int[] navigationItemIcons = new int[]{R.drawable.logout};

    public String getSERVRE_API_PATH_ROOT() {
        return SERVRE_API_PATH_ROOT;
    }

    public String getSERVER_IMAGE_ROOT_PATH() {
        return SERVER_IMAGE_ROOT_PATH;
    }

    public int[] getSWIPER_REFRESH_COLORS() {
        return SWIPER_REFRESH_COLORS;
    }

    public File getSTORAGE_PATH() {
        return STORAGE_PATH;
    }

    public String[] getMenuItemNames() {
        return menuItemNames;
    }

    public int[] getMenuItemIcons() {
        return menuItemIcons;
    }

    public String[] getNavigationItemNames() {
        return navigationItemNames;
    }

    public int[] getNavigationItemIcons() {
        return navigationItemIcons;
    }
}
