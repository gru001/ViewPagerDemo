package com.example.viewpagerdemo.app.profile;

import com.example.viewpagerdemo.app.BasePresenter;
import com.example.viewpagerdemo.app.BaseView;
import com.example.viewpagerdemo.app.profile.models.UserResponseModel;

/**
 * Contract for profile (Timeline view)
 *
 * @author pranit
 * @version 1.0
 * @since 24/3/17
 */

public interface ProfileContract {
    interface View extends BaseView<ProfilePresenter> {
        void showUsers(UserResponseModel response);
    }

    interface UserActionListener extends BasePresenter{
        void getUsers();
    }
}
