package se.ocarina.straightonstrait.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import se.ocarina.straightonstrait.dagger.module.AppModule;
import se.ocarina.straightonstrait.dagger.module.NetModule;
import se.ocarina.straightonstrait.ui.JourneyListFragment;
import se.ocarina.straightonstrait.ui.SettingsFragment;
import se.ocarina.straightonstrait.ui.JourneyFragment;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(JourneyFragment fragment);
    void inject(JourneyListFragment fragment);
    void inject(SettingsFragment fragment);
}
