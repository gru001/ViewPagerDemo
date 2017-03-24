package com.example.viewpagerdemo.app.profile;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.viewpagerdemo.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Provides ui for {@link ProfilePhotosActivity}
 *
 * @author pranit(gru001)
 */

public class ProfilePhotosActivity extends AppCompatActivity{
    @BindView(R.id.view_pager) ViewPager vpgrProfiles;

    private ProfilePagerAdapter mPagerAdapter;
    private final ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    /**
     * Factory method to get start Intent of {@link ProfilePhotosActivity}
     *
     * @param context context of calling activity
     * @return start intent of {@link ProfilePhotosActivity}
     */
    public static Intent getStartIntent(Context context){
        return new Intent(context, ProfilePhotosActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_photos);
        ButterKnife.bind(this);

        setupPager();
    }

    private void setupPager() {
        // Add the OnPageChangeListener
        vpgrProfiles.addOnPageChangeListener(mOnPageChangeListener);

        // Set the ViewPager adapter
        mPagerAdapter = new ProfilePagerAdapter(getSupportFragmentManager());

        // Set a custom animation PageTransformer
        vpgrProfiles.setPageTransformer(false, new CustomAnimationPageTransformer());

        // Call the initialize method to add intro screens
        // and set up the selected styles
        initialize();

        // Set the ViewPager adapter
        vpgrProfiles.setAdapter(mPagerAdapter);

        // Required in order to get a callback in onPageSelected
        vpgrProfiles.setCurrentItem(0);
    }

    private void initialize() {
        addIntroScreen(ProfilePagerFragment.newInstance(getString(R.string.title_image1)),
                ContextCompat.getColor(this, R.color.material_blue));
        addIntroScreen(ProfilePagerFragment.newInstance(getString(R.string.title_image2)),
                ContextCompat.getColor(this, R.color.material_indigo));
        addIntroScreen(ProfilePagerFragment.newInstance(getString(R.string.title_image3)),
                ContextCompat.getColor(this, R.color.material_deep_purple));
    }

    /**
     * Add an intro screen (Fragment) to the ViewPager.
     * @param introScreen Fragment to add.
     */
    protected void addIntroScreen(ProfilePagerFragment introScreen, int backgroundColor) {
        mPagerAdapter.addFragment(introScreen, backgroundColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vpgrProfiles.removeOnPageChangeListener(mOnPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position < mPagerAdapter.getCount() - 1) {
                // Set the background color of the ViewPager based on the scroll offset
                // in respect to the background color of the pages being scrolled
                vpgrProfiles.setBackgroundColor((Integer) mArgbEvaluator.evaluate(positionOffset,
                        mPagerAdapter.getBackgroundColorForPage(position),
                        mPagerAdapter.getBackgroundColorForPage(position + 1)));
            }
        }

        @Override
        public void onPageSelected(int position) {
            // Check if the current ViewPager position is at the end
            /*if (position == mPagerAdapter.getCount() - 1) {
                // Show the "Done" button
                mNextButton.showDoneButton(true);

                // Hide the "Skip" button
                mSkipButton.setVisibility(View.INVISIBLE);
            } else {
                // Check if the "Done" button is shown - if so, we should change to
                // "Next" as we're no longer on the last page in the ViewPager
                if (mNextButton.getButtonStyle() == NextDoneButton.STYLE_DONE) {
                    mNextButton.showNextButton(true);
                }

                // Check if the "Skip" button isn't visible when it should be
                if (mShowSkipButton && mSkipButton.getVisibility() != View.VISIBLE) {
                    mSkipButton.setVisibility(View.VISIBLE);
                }
            }*/

//            setProgressSelection(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };
}
