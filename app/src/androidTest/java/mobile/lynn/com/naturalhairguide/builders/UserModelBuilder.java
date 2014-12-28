package mobile.lynn.com.naturalhairguide.builders;

import mobile.lynn.com.naturalhairguide.model.UserModel;

public class UserModelBuilder extends UserModel {
    public static UserModelBuilder build() {
        return new UserModelBuilder();
    }

    public UserModelBuilder withEmail(String email) {
        this.setEmail(email);
        return this;
    }
}
