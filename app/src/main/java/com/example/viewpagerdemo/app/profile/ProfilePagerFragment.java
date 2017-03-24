package com.example.viewpagerdemo.app.profile;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment for background color animation
 * Use the {@link ProfilePagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * Using {@link CustomAnimationPageTransformerDelegate} for animating title of screen.
 * (can be replaced by any {@link View})
 *
 * @author prait
 */
public class ProfilePagerFragment extends Fragment implements CustomAnimationPageTransformerDelegate{
    private static final String EXTRA_TITLE = "title";

    private String mTitle;
    private Object mExtraArguments;

    // ui
    @BindView(R.id.lblTitle) TextView lblTitle;

    public ProfilePagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * {@link ProfilePagerFragment} fragment.
     *
     * @param title Title for background screen
     * @return A new instance of fragment ProfilePagerFragment.
     */
    public static ProfilePagerFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);

        ProfilePagerFragment fragment = new ProfilePagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getExtraArguments();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_pager, container, false);
    }

    public void getExtraArguments() {
        mTitle = getArguments().getString(EXTRA_TITLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        // Set a tag on the View so we can use it for custom animations
        view.setTag(this);

        // set title
        lblTitle.setText(mTitle);
    }

    @Override
    public void onPageSelected() {
        // Animate a jump
        ObjectAnimator animation = ObjectAnimator.ofFloat(lblTitle, "translationY",
                -20f, 20f, -20f, 20f, -20f, 20f, 0f);
        animation.setDuration(1000);
        animation.start();
    }

    @Override
    public void onPageScrolled(View page, float position) {
        int pageWidth = page.getWidth();
        float absPosition = Math.abs(position);
        float pageWidthTimesPosition = pageWidth * absPosition;

        lblTitle.setTranslationX(pageWidthTimesPosition / 4f);
        lblTitle.setTranslationY(-pageWidthTimesPosition / 2f);
        lblTitle.setRotation(-35 * absPosition);
        lblTitle.setAlpha(1f - absPosition);
    }

    @Override
    public void onPageInvisible(float position) {

    }
}
