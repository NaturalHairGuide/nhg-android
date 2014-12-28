package mobile.lynn.com.naturalhairguide.view;

import junit.framework.TestCase;

import java.util.List;

import mobile.lynn.com.naturalhairguide.builders.NotificationBuilder;
import mobile.lynn.com.naturalhairguide.builders.UserModelBuilder;
import mobile.lynn.com.naturalhairguide.model.Notification;
import mobile.lynn.com.naturalhairguide.model.UserModel;
import mobile.lynn.com.naturalhairguide.viewmodel.UserViewModel;

public class UserViewModelTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_ShouldReturnProfileIncompleteNotificationIfUserHasSetLastWashDate() {
        UserModel user = UserModelBuilder.build();
        UserViewModel viewModel = new UserViewModel(user);

        List<Notification> notifications = viewModel.getNotifications();

        Notification expectedNotification = NotificationBuilder.build().withTitle("Profile Incomplete");

        assertTrue(notifications.contains(expectedNotification));
    }
}