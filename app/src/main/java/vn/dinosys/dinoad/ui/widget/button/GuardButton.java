package vn.dinosys.dinoad.ui.widget.button;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by htsi.
 * Since: 7/12/16 on 10:04 AM
 * Project: DinoAd
 */
public class GuardButton extends Button {

    private ClickGuard mGuard;

    public GuardButton(Context context) {
        super(context);
    }

    public GuardButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuardButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GuardButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mGuard != null) {
            mGuard.rest();
            mGuard = null;
        }
    }

    public void registerAvoidMultipleRapidClicks(){
        if (mGuard == null)
            mGuard = ClickGuard.newGuard();

        mGuard.add(GuardButton.this);
        mGuard.watch();
    }
}