package mobile.lynn.com.naturalhairguide.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.activity.BaseActivity;
import mobile.lynn.com.naturalhairguide.model.Notification;
import mobile.lynn.com.naturalhairguide.model.UserModel;
import mobile.lynn.com.naturalhairguide.viewmodel.UserViewModel;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Natural Hair Guide");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout cardsContainer = (LinearLayout) view.findViewById(R.id.cards);

        UserModel currentUser = ((BaseActivity) getActivity()).getCurrentUser();
        UserViewModel viewModel = new UserViewModel(currentUser, getActivity());

        List<Notification> notifications = viewModel.getNotifications();
        for(final Notification notification : notifications) {
            View notificationCardView = inflater.inflate(R.layout.layout_card_notification, container, false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 10, 0, 10);

            TextView title = (TextView) notificationCardView.findViewById(R.id.card_title);
            TextView description = (TextView) notificationCardView.findViewById(R.id.card_description);
            View colorBar = notificationCardView.findViewById(R.id.color_bar);

            title.setText(notification.getTitle());
            description.setText(notification.getDescription());
            colorBar.setBackgroundColor(notification.getColor());

            notificationCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().startActivity(notification.getIntents().get(0));
                }
            });

            cardsContainer.addView(notificationCardView, params);
        }

        if(notifications.isEmpty()) {
            TextView empty = (TextView) view.findViewById(R.id.no_notifications);
            empty.setText("No notifications");
        }

        return view;
    }
}
