package dean.com.template.injection.module;

import android.provider.Settings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dean.com.template.application.TemplateApplication;
import dean.com.template.data.storage.TemplatePreferences;
import dean.com.template.data.storage.PreferenceRepository;
import dean.com.template.data.storage.SecureSharedPreferences;

@Module
public final class DataModule {

    private static final String PREFS_NAME = "infinumSecureStorage";

    @Singleton
    @Provides
    TemplatePreferences provideTemplatePreferences(final SecureSharedPreferences securePreferences) {
        return TemplatePreferences.create(securePreferences);
    }

    @Provides
    @Singleton
    PreferenceRepository providePreferenceRepository(final TemplatePreferences templatePreferences) {
        return templatePreferences;
    }

    @Provides
    @Singleton
    public SecureSharedPreferences provideSecureSharedPreferences(final TemplateApplication context) {
        final String androidSecret = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return new SecureSharedPreferences(context, context.getSharedPreferences(PREFS_NAME, 0), androidSecret);
    }
}
