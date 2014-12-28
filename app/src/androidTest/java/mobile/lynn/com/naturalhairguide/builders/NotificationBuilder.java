package mobile.lynn.com.naturalhairguide.builders;

import mobile.lynn.com.naturalhairguide.model.Notification;

public class NotificationBuilder extends Notification {
    public static NotificationBuilder build() {
        return new NotificationBuilder();
    }

    public Notification withTitle(String s) {
        this.setTitle(s);
        return this;
    }
}
