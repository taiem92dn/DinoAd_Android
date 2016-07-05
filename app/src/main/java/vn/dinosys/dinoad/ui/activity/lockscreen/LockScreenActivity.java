package vn.dinosys.dinoad.ui.activity.lockscreen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;
import com.r0adkll.slidr.model.SlidrPosition;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.lockscreen.LockScreenFragment;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:50 AM
 * Project: DinoAd
 */
public class LockScreenActivity extends BaseActivity implements HasComponent<AppComponent> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LockScreenActivity", "onCreate");

        //SlidrPosition position = SlidrPosition.values()[Utils.getRandom().nextInt(numPositions)];
        //mPosition.setText(position.name());

        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)
                .scrimColor(Color.TRANSPARENT)
                .edgeSize(0.25f)
                .build();

        Slidr.attach(this, config);
    }

    @Override
    protected BaseFragment hostFragment() {
        return LockScreenFragment.newInstance();
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LockScreenActivity", "onDestroy");

    }
}
