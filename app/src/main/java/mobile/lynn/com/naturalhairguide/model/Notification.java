package mobile.lynn.com.naturalhairguide.model;

import android.content.Intent;

import java.util.List;

public class Notification {
    private String title;
    private String description;
    private int notificationColorId;
    private List<Intent> intents;

    public Notification(int color, List<Intent> intents, String title, String description) {
        this.notificationColorId = color;
        this.intents = intents;
        this.title = title;
        this.description = description;
    }

    public Notification(int color, List<Intent> intents) {
        this(color, intents, "", "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addIntent(Intent intent) {
        intents.add(intent);
    }

    public void setColor(int colorId) {
        this.notificationColorId = colorId;
    }

    public int getColor() {
        return notificationColorId;
    }

    public List<Intent> getIntents() {
        return intents;
    }
}
