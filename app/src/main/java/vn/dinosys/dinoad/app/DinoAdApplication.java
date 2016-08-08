package vn.dinosys.dinoad.app;

import android.support.multidex.MultiDexApplication;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.di.component.DaggerAppComponent;
import vn.dinosys.dinoad.di.module.AppModule;
import vn.dinosys.dinoad.util.NotificationHelper;

/**
 * Created by htsi.
 * Since: 7/1/16 on 5:21 PM
 * Project: DinoAd
 */
public class DinoAdApplication extends MultiDexApplication {

    private static DinoAdApplication sInstance;

    private AppComponent mAppComponent;

    public DinoAdApplication() {
        sInstance = this;
    }

    public static DinoAdApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        NotificationHelper.init(this);

        //  Set default font to MyriadPro
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.APP_DEFAULT_FONT)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
