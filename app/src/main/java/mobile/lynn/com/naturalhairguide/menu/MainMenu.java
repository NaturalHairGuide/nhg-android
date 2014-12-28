package mobile.lynn.com.naturalhairguide.menu;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.activity.DictionaryActivity;
import mobile.lynn.com.naturalhairguide.activity.ProfileActivity;
import mobile.lynn.com.naturalhairguide.activity.SettingsActivity;
import mobile.lynn.com.naturalhairguide.adapter.MenuAdapter;
import mobile.lynn.com.naturalhairguide.fragment.HomeFragment;
import mobile.lynn.com.naturalhairguide.helper.Tags;

public class MainMenu implements AdapterView.OnItemClickListener {
    private final ActionBarActivity context;
    private final String[] mainMenuItems;
    private final DrawerLayout menuLayout;
    private final ListView mainMenuList;
    private final ListView secondaryMenuList;
    private final String[] secondaryMenuItems;
    private final LinearLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    public MainMenu(ActionBarActivity activity) {
        this.context = activity;

        mainMenuItems = context.getResources().getStringArray(R.array.menu);
        secondaryMenuItems = context.getResources().getStringArray(R.array.second_menu);
        menuLayout = (DrawerLayout) context.findViewById(R.id.menu_layout);

        drawer = (LinearLayout) context.findViewById(R.id.left_menu);
        mainMenuList = (ListView) context.findViewById(R.id.main_menu);
        secondaryMenuList = (ListView) context.findViewById(R.id.secondary_menu);

        mainMenuList.setAdapter(new MenuAdapter(context, R.layout.list_item_main_menu, R.id.name, mainMenuItems));
        mainMenuList.setOnItemClickListener(this);

        secondaryMenuList.setAdapter(new MenuAdapter(context, R.layout.list_item_secondary_menu, R.id.name, secondaryMenuItems));
        secondaryMenuList.setOnItemClickListener(this);

        mTitle = mDrawerTitle = context.getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(context, menuLayout,
                null,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                context.invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                context.invalidateOptionsMenu();
            }
        };

        if(menuLayout != null) {
            menuLayout.post(new Runnable() {
                @Override
                public void run() {
                    mDrawerToggle.syncState();
                }
            });

            menuLayout.setDrawerListener(mDrawerToggle);

            context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            context.getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        context.getSupportActionBar().setIcon(android.R.color.transparent);
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        if(parent.getId() == R.id.main_menu) {
            selectItemFromMainMenu(position);
        } else {
            selectItemFromSecondMenu(position);
        }
    }

    private void selectItemFromSecondMenu(int position) {
        switch (position) {
            case 0:
                context.startActivity(new Intent(context, SettingsActivity.class));
                break;
            case 1:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Start your natural hair journey today");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this app!");
                context.startActivity(Intent.createChooser(intent, "Share"));
                break;
            case 2:
                logout();
                break;
        }
    }

    private void selectItemFromMainMenu(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                startFragment(position, fragment);
                break;
            case 1:
                context.startActivity(new Intent(context, DictionaryActivity.class));
                break;
            case 2:
                context.startActivity(new Intent(context, ProfileActivity.class));
            default:
                fragment = new HomeFragment();
                startFragment(position, fragment);
                break;
        }
    }

    private void startFragment(int position, Fragment fragment) {
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();

        mainMenuList.setItemChecked(position, true);
        if(menuLayout != null) menuLayout.closeDrawer(drawer);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void toggleMenu() {
        if(menuLayout.isDrawerOpen(GravityCompat.START)) {
            menuLayout.closeDrawer(drawer);
        } else {
            menuLayout.openDrawer(drawer);
        }
    }

    public void logout() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(Tags.CREDENTIALS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.remove(Tags.USER_ID);
        editor.apply();
        context.finish();
    }
}
