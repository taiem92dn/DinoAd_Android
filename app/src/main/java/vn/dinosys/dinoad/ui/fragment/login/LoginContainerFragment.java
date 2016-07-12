package vn.dinosys.dinoad.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.widget.viewpager.ScrollerSlowAnimation;
import vn.dinosys.dinoad.ui.widget.viewpager.VerticalViewPager;

/**
 * Created by htsi.
 * Since: 7/7/16 on 1:48 PM
 * Project: DinoAd
 */
public class LoginContainerFragment extends BaseFragment {


    @BindView(R.id.viewPageHost)
    VerticalViewPager mViewPageHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_container, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {

        try {
            Class<?> viewpager = VerticalViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            ScrollerSlowAnimation mScroller = new ScrollerSlowAnimation(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(mViewPageHost, mScroller);
        } catch (NoSuchFieldException | IllegalAccessException pE) {
            pE.printStackTrace();
        }

        mViewPageHost.setPageTransformer(false, new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.55f;

            public void transformPage(View view, float position) {
                //int pageWidth = view.getWidth();

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
        mViewPageHost.setAdapter(new LoginAdapter(getChildFragmentManager()));
    }

    public void tooglePage() {
        if (mViewPageHost.getCurrentItem() > 0)
            mViewPageHost.setCurrentItem(0, true);
        else
            mViewPageHost.setCurrentItem(1, true);
    }

    private class LoginAdapter extends FragmentPagerAdapter {

        public LoginAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new SignInFragment();
            } else {
                return new SignUpFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
