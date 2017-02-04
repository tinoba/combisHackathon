package dean.com.template.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dean.com.template.application.TemplateApplication;
import dean.com.template.device.ApplicationInformation;
import dean.com.template.device.ApplicationInformationImpl;
import dean.com.template.device.DeviceInformation;
import dean.com.template.device.DeviceInformationImpl;

@Module
public final class DeviceModule {

    @Provides
    @Singleton
    public DeviceInformation prvoideDeviceInformation() {
        return new DeviceInformationImpl();
    }

    @Provides
    @Singleton
    public ApplicationInformation provideApplicationInformation(final TemplateApplication application) {
        return new ApplicationInformationImpl(application, application.getPackageManager());
    }
}
