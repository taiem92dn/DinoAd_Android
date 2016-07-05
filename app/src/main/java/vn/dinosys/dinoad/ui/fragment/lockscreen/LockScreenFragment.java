package vn.dinosys.dinoad.ui.fragment.lockscreen;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.presenter.lockscreen.LockScreenPresenter;
import vn.dinosys.dinoad.ui.activity.MainActivity;
import vn.dinosys.dinoad.ui.activity.lockscreen.YoutubePlayerActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.ILockScreenView;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:50 AM
 * Project: DinoAd
 */
public class LockScreenFragment extends BaseFragment implements ILockScreenView {

    private static final String TAG = LockScreenFragment.class.getName();

    @Inject
    LockScreenPresenter mLockScreenPresenter;

    @BindView(R.id.imgBackground)
    ImageView mImgBackground;

    @BindView(R.id.textHour)
    TextView mTextHour;

    @BindView(R.id.textDate)
    TextView mTextDate;

    @BindView(R.id.textSlideUnlock)
    ShimmerTextView mTextSlideToUnlock;

    @BindView(R.id.viewPageBanner)
    VerticalViewPager mViewPageBanner;

    public static LockScreenFragment newInstance() {
        return new LockScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lockscreen, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setupUI() {

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getContext());
        Drawable drawable = wallpaperManager.getDrawable();
        mImgBackground.setImageDrawable(drawable);

        updateTextDateTime();

        setupViewPageBanner();

        Shimmer shimmer = new Shimmer();
        shimmer.setDuration(2000).start(mTextSlideToUnlock);

        initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        updateTextDateTime();
    }

    private void initialize() {
        this.getComponent(AppComponent.class).inject(this);
        mLockScreenPresenter.setView(this);
    }

    private void updateTextDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm", Locale.getDefault());
        String time = sdfTime.format(calendar.getTime());

        SimpleDateFormat sdfTimeId = new SimpleDateFormat("a", Locale.getDefault());
        String timeID = sdfTimeId.format(calendar.getTime());

        mTextHour.setText(String.format(getResources().getString(R.string.time_format), time, timeID));

        sdfTime = new SimpleDateFormat("EE, dd MMM", Locale.getDefault());
        String date = sdfTime.format(calendar.getTime());
        mTextDate.setText(date);
    }

    private void setupViewPageBanner() {
        mViewPageBanner.setPageTransformer(true, new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.75f;

            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    view.setAlpha(1);
                    view.setTranslationY(0);
                    view.setScaleX(1);
                    view.setScaleY(1);

                } else if (position <= 1) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(1 - position);

                    // Counteract the default slide transition
                    //view.setTranslationY(pageWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
        mViewPageBanner.setAdapter(new BannerAdapter(getChildFragmentManager()));
    }

    @Override
    public void renderBannerList(List<Banner> pBannerList) {

    }


    public class BannerAdapter extends FragmentPagerAdapter {

        public BannerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a BannerFragment (defined as a static inner class below).
            return BannerFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BannerFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static final String DEVELOPER_KEY = "AIzaSyAkcnHqlwdAJczlCDGs6zicr8P7Zbxr3Xs";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static BannerFragment newInstance(int sectionNumber) {
            BannerFragment fragment = new BannerFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public BannerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ImageView banner = new ImageView(getContext());
            banner.setScaleType(ImageView.ScaleType.CENTER_CROP);
            banner.setImageResource(R.drawable.example_banner);
            banner.setOnClickListener(pView -> {
                startActivity(new Intent(getActivity(), YoutubePlayerActivity.class));
            });
            return banner;
        }
    }
}
