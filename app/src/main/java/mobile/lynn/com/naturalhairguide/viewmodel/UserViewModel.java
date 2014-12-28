package mobile.lynn.com.naturalhairguide.viewmodel;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.activity.BaseActivity;
import mobile.lynn.com.naturalhairguide.activity.ProfileActivity;
import mobile.lynn.com.naturalhairguide.activity.QuestionnaireActivity;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.HairType;
import mobile.lynn.com.naturalhairguide.model.Notification;
import mobile.lynn.com.naturalhairguide.model.UserModel;

public class UserViewModel {
    private final UserModel user;
    private final FragmentActivity context;
    List<Notification> notifications = new ArrayList<Notification>();
    private Timestamp defaultTime = new Timestamp(0);

    public UserViewModel(UserModel user, FragmentActivity context) {
        this.user = user;
        this.context = context;
    }

    public List<Notification> getNotifications() {
        int completion = getProfileCompletion();

        if (completion != 100) {
            int color = context.getResources().getColor(R.color.primary_color);
            Intent intent = new Intent(context, ProfileActivity.class);

            notifications.add(new Notification(color, Arrays.asList(intent), "Please complete your profile",
                    "Your profile is " + completion + "% complete"));
        }

        return notifications;
    }

    private int getProfileCompletion() {
        int completion = 100;

        if (user.getHairType().equals(HairType.NONE)) {
            completion -= 20;

            int color = context.getResources().getColor(R.color.secondary_color_two);
            Intent intent = new Intent(context, QuestionnaireActivity.class);
            intent.putExtra(Tags.USER_ID, ((BaseActivity) context).getCurrentUser().getId());
            intent.putExtra(Tags.QUESTIONNAIRE_TYPE, "type");

            Notification hairTypeQuiz = new Notification(color, Arrays.asList(intent), "Set your Hair Type",
                    "Don't know what your hair type is? Take this quiz to determine.");

            notifications.add(hairTypeQuiz);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1970 );
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        long time = cal.getTime().getTime();
        Date defaultDate = new Date(time);

        if (user.getLastDeepCondition().toString().equals(defaultDate.toString())) {
            completion -= 20;
        }

        if (user.getLastHairWash().toString().equals(defaultDate.toString())) {
            completion -= 20;
        }

        if (user.getLastHotOilTreatment().toString().equals(defaultDate.toString())) {
            completion -= 20;
        }

        Date today = new Date(Calendar.getInstance().getTime().getTime());
        if (user.getNextCheckup().equals(today) || user.getNextCheckup().before(today)) {
            completion -= 20;

            int color = context.getResources().getColor(R.color.secondary_color_two);
            Intent intent = new Intent(context, QuestionnaireActivity.class);
            intent.putExtra(Tags.USER_ID, ((BaseActivity) context).getCurrentUser().getId());
            intent.putExtra(Tags.QUESTIONNAIRE_TYPE, "checkup");

            String frequency = user.getCheckupFrequency().toString();

            Notification hairTypeQuiz = new Notification(color, Arrays.asList(intent), "How healthy is your hair?",
                    "Every " + frequency + " we will see how your hair is. Take this quiz to find out how healthy " +
                            "your hair is today. Also you can change the frequency in which we check.");

            notifications.add(hairTypeQuiz);
        }

        return completion;
    }
}
