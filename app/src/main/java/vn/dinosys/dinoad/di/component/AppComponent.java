package vn.dinosys.dinoad.di.component;

import javax.inject.Singleton;

import dagger.Component;
import vn.dinosys.dinoad.di.module.AppModule;

/**
 * Created by htsi.
 * Since: 7/1/16 on 6:02 PM
 * Project: DinoAd
 */
@Singleton
@Component( modules = {
        AppModule.class
})
public interface AppComponent {
}
