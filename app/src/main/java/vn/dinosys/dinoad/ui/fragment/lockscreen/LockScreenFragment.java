package vn.dinosys.dinoad.ui.fragment.lockscreen;

import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.presenter.lockscreen.LockScreenPresenter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.ILockScreenView;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:50 AM
 * Project: DinoAd
 */
public class LockScreenFragment extends BaseFragment implements ILockScreenView {

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

    private void setupUI() {
        initialize();

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getContext());
        Drawable drawable = wallpaperManager.getDrawable();
        //ImageView imgBackground = ((ImageView)getView().findViewById(R.id.imgBackground));
        //imgBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImgBackground.setImageDrawable(drawable);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm", Locale.getDefault());
        String time = sdfTime.format(calendar.getTime());

        SimpleDateFormat sdfTimeId = new SimpleDateFormat("a", Locale.getDefault());
        String timeID = sdfTimeId.format(calendar.getTime());

        mTextHour.setText(String.format(getResources().getString(R.string.time_format), time, timeID));

        sdfTime = new SimpleDateFormat("EE, dd MMM", Locale.getDefault());
        String date = sdfTime.format(calendar.getTime());
        mTextDate.setText(date);

        Shimmer shimmer = new Shimmer();
        shimmer.start(mTextSlideToUnlock);

    }

    private void initialize() {
        this.getComponent(AppComponent.class).inject(this);
        mLockScreenPresenter.setView(this);
        //mLockScreenPresenter.loadBanners();
    }

    @Override
    public void renderBannerList(List<Banner> pBannerList) {

    }
}
