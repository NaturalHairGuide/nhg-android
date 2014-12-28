package mobile.lynn.com.naturalhairguide.adapter;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.IconTextView;
import android.widget.ImageView;
import android.widget.TextView;

import mobile.lynn.com.naturalhairguide.R;

public class MenuAdapter extends ArrayAdapter<String> {
    private final String[] menuItems;
    private final int resource;

    public MenuAdapter(ActionBarActivity context, int resource, int textViewResourceId, String[] items) {
        super(context, resource, textViewResourceId, items);
        this.resource = resource;
        this.menuItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.name);
        textView.setText(menuItems[position]);

        IconTextView icon = (IconTextView) rowView.findViewById(R.id.menu_icon);
        icon.setText(getIcon(menuItems[position]));

        return rowView;
    }

    private String getIcon(String menuItem) {
        if(menuItem.equals("Home")) {
            return "{fa-home}";
        } else if (menuItem.equals("Dictionary")) {
            return "{fa-book}";
        } else if (menuItem.equals("My Profile")) {
            return "{fa-user}";
        } else if (menuItem.equals("Settings")) {
            return "{fa-gear}";
        } else if (menuItem.equals("Share")) {
            return "{fa-share-alt}";
        } else if (menuItem.equals("Log out")) {
            return "{fa-power-off}";
        }

        return "";
    }

    private int getImage(int position) {
        String menuItem = menuItems[position];

        if(menuItem.equals("Share")) {
            return android.R.drawable.ic_menu_share;
        } else if(menuItem.equals("Settings")) {
            return android.R.drawable.ic_menu_manage;
        }

        return 0;
    }
}
