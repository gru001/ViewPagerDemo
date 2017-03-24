package com.example.viewpagerdemo.app.profile;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.viewpagerdemo.app.AppConstants;
import com.example.viewpagerdemo.app.R;
import com.example.viewpagerdemo.app.profile.data.ProfileRepository;
import com.example.viewpagerdemo.app.profile.models.UserResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Provides ui for {@link ProfilePhotosActivity}
 *
 * @author pranit(gru001)
 */

public class ProfilePhotosActivity extends AppCompatActivity implements ProfileContract.View, TimelineAdapter.ItemClickListener {
    @BindView(R.id.view_pager) ViewPager vpgrProfiles;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.recl_timeline) RecyclerView reclTimeline;

    private ProfilePagerAdapter mPagerAdapter;
    private final ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    private TimelineAdapter mAdapter;
    private ProfilePresenter mPresenter;

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

        new ProfilePresenter(ProfileRepository.getInstance(),this);

        setupPager();
        setupRecycler();

        mPresenter.getUsers();
    }

    private void setupRecycler() {
        // Set the timeline adapter
        mAdapter = new TimelineAdapter(this);
        reclTimeline.setAdapter(mAdapter);
        reclTimeline.setHasFixedSize(true);
        reclTimeline.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
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
        public void onPageSelected(int position) {}

        @Override
        public void onPageScrollStateChanged(int state) {}
    };

    @Override
    public void setPresenter(ProfilePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            mProgressBar.setVisibility(View.VISIBLE);
        }else{
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this,"Something went wrong.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsers(UserResponseModel response) {
        Toast.makeText(this,response.getMessage(),Toast.LENGTH_SHORT).show();
        if(response.getSuccess() == AppConstants.SUCCESS) {
            mAdapter.setUsers(response.getReactions());
            reclTimeline.setVerticalScrollbarPosition(0);
        }
    }

    @Override
    public void onItemClick(int pos) {

    }
}
