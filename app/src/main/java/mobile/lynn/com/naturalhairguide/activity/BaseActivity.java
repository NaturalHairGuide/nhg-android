package mobile.lynn.com.naturalhairguide.activity;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.UserModel;

public class BaseActivity extends ActionBarActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void popBackStack() {
        getSupportFragmentManager().popBackStack();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    public UserModel getCurrentUser() {
        SharedPreferences mySharedPreferences = getSharedPreferences(Tags.CREDENTIALS, MODE_PRIVATE);
        long userId = mySharedPreferences.getLong(Tags.USER_ID, 0);
        return UserModel.load(UserModel.class, userId);
    }
}
