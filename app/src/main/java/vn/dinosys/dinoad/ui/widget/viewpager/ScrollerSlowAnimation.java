package vn.dinosys.dinoad.ui.widget.viewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by htsi.
 * Since: 7/12/16 on 2:14 PM
 * Project: DinoAd
 */
public class ScrollerSlowAnimation extends Scroller {

    public ScrollerSlowAnimation(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int) (duration * 8));
    }
}
