package com.example.viewpagerdemo.app.profile;

import com.example.viewpagerdemo.app.profile.data.ProfileRepository;
import com.example.viewpagerdemo.app.profile.models.UserResponseModel;

import java.io.IOException;

/**
 * Presenter for Profile
 *
 * @author pranit
 * @version 1.0
 * @since 24/3/17
 */

public class ProfilePresenter implements ProfileContract.UserActionListener{
    private final ProfileRepository mRepository;
    private final ProfileContract.View mView;

    /**
     * Constructor of profile presenter
     *
     * @param repository instance of repository
     * @param view instance of View {@link ProfileContract.View}
     */
    public ProfilePresenter(ProfileRepository repository, ProfileContract.View view){
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getUsers() {
        mView.setLoadingIndicator(true);
        try {
            UserResponseModel userResponseModel = mRepository.getUsers();
            if(userResponseModel != null){
                mView.showUsers(userResponseModel);
            }else {
                mView.showError();
            }
            mView.setLoadingIndicator(false);
        } catch (IOException e) {
            e.printStackTrace();
            mView.showError();
            mView.setLoadingIndicator(false);
        }
    }
}
