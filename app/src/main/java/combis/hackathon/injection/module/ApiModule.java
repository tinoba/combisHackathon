package combis.hackathon.injection.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import combis.hackathon.data.api.NetworkInterceptor;
import combis.hackathon.data.api.converter.MovieAPIConverter;
import combis.hackathon.data.api.converter.MovieAPIConverterImpl;
import combis.hackathon.data.service.TemplateAPI;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.service.NetworkServiceImpl;
import combis.hackathon.device.ApplicationInformation;
import combis.hackathon.device.DeviceInformation;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static combis.hackathon.data.api.APIConstants.BASE_URL;


@Module
public final class ApiModule {

    private static final int CONNECTION_TIMEOUT = 10;

    @Provides
    @Singleton
    Retrofit provideRetrofit(final OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService(final TemplateAPI templateAPI) {
        return new NetworkServiceImpl(templateAPI);
    }


    @Provides
    @Singleton
    MovieAPIConverter providePersonAPIConverter() {
        return new MovieAPIConverterImpl();
    }


    @Provides
    @Singleton
    TemplateAPI provideInventoryAPI(final Retrofit retrofit) {
        return retrofit.create(TemplateAPI.class);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor loggingInterceptor, final NetworkInterceptor networkInterceptor) {
        final OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(networkInterceptor);
        okhttpBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS).interceptors().add(loggingInterceptor);

        return okhttpBuilder.build();

    }

    @Provides
    @Singleton
    public NetworkInterceptor provideNetworkInterceptor(final DeviceInformation deviceInformation,
            final ApplicationInformation applicationInformation) {
        final int osVersion = deviceInformation.getOsVersionInt();
        final String appVersionName = applicationInformation.getVersionName();

        return new NetworkInterceptor(osVersion, appVersionName);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }
}
