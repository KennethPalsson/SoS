package se.ocarina.straightonstrait;

import android.app.Application;

import se.ocarina.straightonstrait.dagger.component.DaggerNetComponent;
import se.ocarina.straightonstrait.dagger.component.NetComponent;
import se.ocarina.straightonstrait.dagger.module.AppModule;
import se.ocarina.straightonstrait.dagger.module.NetModule;

public class SosApplication extends Application {
    private static final String BASE_URL = "http://xmlopen.rejseplanen.dk/bin/rest.exe/";

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
