package combis.hackathon.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import combis.hackathon.application.TemplateApplication;
import combis.hackathon.device.ApplicationInformation;
import combis.hackathon.device.ApplicationInformationImpl;
import combis.hackathon.device.DeviceInformation;
import combis.hackathon.device.DeviceInformationImpl;

@Module
public final class DeviceModule {

    @Provides
    @Singleton
    public DeviceInformation provideDeviceInformation() {
        return new DeviceInformationImpl();
    }

    @Provides
    @Singleton
    public ApplicationInformation provideApplicationInformation(final TemplateApplication application) {
        return new ApplicationInformationImpl(application, application.getPackageManager());
    }
}
