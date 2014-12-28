package mobile.lynn.com.naturalhairguide.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.UserModel;
import mobile.lynn.com.naturalhairguide.service.DictionaryService;
import mobile.lynn.com.naturalhairguide.view.CustomEditTextView;

public class LoginActivity extends PlusBaseActivity implements LoaderCallbacks<Cursor> {
    // Keep track of the login task to ensure we can cancel it if requested.
    private UserLoginTask mAuthTask = null;

    // UI references.
    private View mProgressView;
    private LinearLayout mEmailLoginFormView;
    private View mLoginFormView;
    private Button registerButton;
    private Button signInWithEmail;
    private TextView errorMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DictionaryService.initialize();

        if(getLoggedInUser() == 0 || getLoggedInUser() == -1) {
            setupGooglePlusLogin();
            setupButtons();

            errorMessageView = (TextView) findViewById(R.id.error_message);
            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);
            mEmailLoginFormView = (LinearLayout) findViewById(R.id.email_login_form);
            mEmailLoginFormView.setVisibility(View.GONE);
        } else {
            openApp();
        }
    }

    private void setupButtons() {
        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmailLoginFormView.setVisibility(View.VISIBLE);
                signInWithEmail.setVisibility(View.VISIBLE);
                registerButton.setVisibility(View.GONE);

                mEmailLoginFormView.removeAllViews();
                setupRegistrationForm(getResources().getString(R.string.action_register));
            }
        });

        signInWithEmail = (Button) findViewById(R.id.sign_in_with_email_button);
        signInWithEmail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailLoginFormView.setVisibility(View.VISIBLE);
                registerButton.setVisibility(View.VISIBLE);
                signInWithEmail.setVisibility(View.GONE);

                mEmailLoginFormView.removeAllViews();
                setupLoginForm(getResources().getString(R.string.action_sign_in));
            }
        });
    }

    private void setupRegistrationForm(String label) {
        final EditText email = addEmailField();

        Button registerBtn = (Button) getLayoutInflater().inflate(R.layout.layout_primary_button, null);
        registerBtn.setText(label);
        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                register(email);
            }
        });
        mEmailLoginFormView.addView(registerBtn);
    }

    private void setupLoginForm(String label) {
        final EditText email = addEmailField();

        Button signInBtn = (Button) getLayoutInflater().inflate(R.layout.layout_primary_button, null);
        signInBtn.setText(label);
        signInBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(email);
            }
        });
        mEmailLoginFormView.addView(signInBtn);
    }

    private EditText addEmailField() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 100, 0, 50);

        CustomEditTextView email = (CustomEditTextView) getLayoutInflater().inflate(R.layout.layout_text_input, null);
        email.setHint(getResources().getString(R.string.prompt_email));
        mEmailLoginFormView.addView(email, params);

        return email;
    }

    private void setupGooglePlusLogin() {
        SignInButton mPlusSignInButton = (SignInButton) findViewById(R.id.plus_sign_in_button);

        if (supportsGooglePlayServices()) {
            mPlusSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
        } else {
            mPlusSignInButton.setVisibility(View.GONE);
        }
    }

    protected void register(EditText emailView) {
        boolean cancel = false;
        String email = emailView.getText().toString();

        if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            cancel = true;
        }

        if (!cancel) {
            UserModel newUser = new UserModel(email);
            newUser.save();

            if(newUser.getId() != -1) {
                saveLoggedInUser(newUser.getId());
                openApp();
            } else {
                errorMessageView.setText(getString(R.string.error_something_went_wrong));
            }
        }
    }

    public void saveLoggedInUser(Long userId) {
        SharedPreferences mySharedPreferences = getSharedPreferences(Tags.CREDENTIALS, MODE_PRIVATE);

        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong(Tags.USER_ID, userId);
        editor.apply();
    }

    private void openApp() {
        long id = getLoggedInUser();

        if(id != 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Tags.USER_ID, id);
            startActivity(intent);
            finish();
        }
    }

    public long getLoggedInUser() {
        SharedPreferences mySharedPreferences = getSharedPreferences(Tags.CREDENTIALS, MODE_PRIVATE);
        return mySharedPreferences.getLong(Tags.USER_ID, 0);
    }

    public void attemptLogin(EditText emailView) {
        if (mAuthTask != null) {
            return;
        }

        emailView.setError(null);
        String email = emailView.getText().toString();

        showProgress(true);
        mAuthTask = new UserLoginTask(email);
        mAuthTask.execute((Void) null);
    }

    protected boolean isEmailValid(String email) {
        boolean isValidFormat = email.matches("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$");
        boolean isExistingUser = new Select().from(UserModel.class).where("email = '" + email + "'").exists();

        return !isExistingUser && isValidFormat;
    }

    protected boolean isPasswordValid(String password) {
        return password.matches("^[a-z0-9_-]{6,18}$");
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onPlusClientSignIn() {

    }

    @Override
    protected void onPlusClientBlockingUI(boolean show) {
        showProgress(show);
    }

    @Override
    protected void updateConnectButtonState() {
//        showProgress(true);
//        if(getPlusClient().isConnected()) {
//        openApp();
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
    }

    @Override
    protected void onPlusClientRevokeAccess() {
        // TODO: Access to the user's G+ account has been revoked.  Per the developer terms, delete
        // any stored user data here.
        Log.d("tag", "");
    }

    @Override
    protected void onPlusClientSignOut() {

    }

    /**
     * Check if the device supports Google Play Services.  It's best
     * practice to check first rather than handling this as an error case.
     *
     * @return whether the device supports Google Play Services
     */
    private boolean supportsGooglePlayServices() {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) ==
                ConnectionResult.SUCCESS;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;

        UserLoginTask(String email) {
            mEmail = email;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            List<UserModel> users = new Select().all().from(UserModel.class).execute();

            for (UserModel user : users) {
                if (user.getEmail().equals(mEmail)) {
                    saveLoggedInUser(user.getId());
                    return true;
                }
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                openApp();
            } else {
                errorMessageView.setText(getString(R.string.error_incorrect_credentials));
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}



