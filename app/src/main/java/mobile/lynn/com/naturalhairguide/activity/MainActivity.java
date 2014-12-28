package mobile.lynn.com.naturalhairguide.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;

import java.util.Arrays;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.fragment.HomeFragment;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.menu.MainMenu;
import mobile.lynn.com.naturalhairguide.model.HairType;
import mobile.lynn.com.naturalhairguide.model.UserModel;


public class MainActivity extends BaseActivity {
    private MainMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean skip = getIntent().getExtras().getBoolean(Tags.SKIP);

        createNotification();

        UserModel currentUser = getCurrentUser();
        if (!skip && currentUser != null && currentUser.getHairType().equals(HairType.NONE)) {
            Intent intent = new Intent(this, QuestionnaireActivity.class);
            intent.putExtra(Tags.USER_ID, currentUser.getId());
            intent.putExtra(Tags.QUESTIONNAIRE_TYPE, "type");
            startActivity(intent);
        } else {
            menu = new MainMenu(this);
            startFragment();
        }
    }

    private void createNotification() {
        int notificationId = 001;

        Intent resultIntent = new Intent(this, QuestionnaireActivity.class);
        resultIntent.putExtra(Tags.QUESTIONNAIRE_TYPE, "checkup");
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Hair Checkup")
                        .setContentText("How is your hair doing? Take this short quiz to find out.")
//                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .addAction(android.R.drawable.ic_dialog_email, "Take Quiz", resultPendingIntent);


        notificationBuilder.setContentIntent(resultPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void startFragment() {
        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                menu.toggleMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
