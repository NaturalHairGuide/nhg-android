package mobile.lynn.com.naturalhairguide.activity;

import android.test.InstrumentationTestCase;

import com.activeandroid.query.*;

import java.util.List;

import mobile.lynn.com.naturalhairguide.builders.UserModelBuilder;
import mobile.lynn.com.naturalhairguide.model.UserModel;

public class LoginActivityTest extends InstrumentationTestCase {

    private LoginActivity loginActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loginActivity = new LoginActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        List<UserModel> allUsers = new Select().all().from(UserModel.class).execute();

        for(UserModel u : allUsers) {
            u.delete();
        }
    }

//    public void test_shouldRegisterNewUserIntoDatabase() {
//        String expectedUsername = "email@email.com";
//        String expectedPassword = "password";
//        loginActivity.register(expectedUsername, expectedPassword);
//
//        UserModel actual = new Select().from(UserModel.class).executeSingle();
//        assertNotNull(actual);
//        assertEquals(expectedUsername, actual.getEmail());
//        assertEquals(expectedPassword, actual.getPassword());
//    }

    public void test_shouldReturnTrueForValidEmailAddress() {
        String validEmail = "email@test.com";
        assertTrue(loginActivity.isEmailValid(validEmail));
    }

    public void test_shouldReturnFalseForInvalidEmailAddress() {
        String validEmail = "invalid;email/address.com";
        assertFalse(loginActivity.isEmailValid(validEmail));
    }

    public void test_shouldReturnFalseForEmailAddressThatAlreadyExists() {
        String email = "existing@email.com";
        UserModel user = UserModelBuilder.build().withEmail(email);
        user.save();

        assertFalse(loginActivity.isEmailValid(email));
    }

    public void test_shouldReturnFalseForEmptyEmailAddress() {
        String emptyEmail = "";
        assertFalse(loginActivity.isEmailValid(emptyEmail));
    }

    public void test_shouldReturnTrueForValidPassword() {
        String validPassword = "myp4ssw0rd";
        assertTrue(loginActivity.isPasswordValid(validPassword));
    }

    public void test_shouldReturnFalseForInvalidPassword() {
        String invalidPassword = "myp4$$w0rd";
        assertFalse(loginActivity.isPasswordValid(invalidPassword));
    }

    public void test_shouldReturnFalseForEmptyPassword() {
        String emptyPassword = "";
        assertFalse(loginActivity.isPasswordValid(emptyPassword));
    }
}
