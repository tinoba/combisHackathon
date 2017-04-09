package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.request.ImageRequest;
import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.Aktivnost;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.api.models.response.PlansResponse;
import combis.hackathon.data.api.models.response.Transport;
import combis.hackathon.data.api.models.response.UploadImageResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static combis.hackathon.data.api.APIConstants.PATH_IMAGE;
import static combis.hackathon.data.api.APIConstants.PATH_LOGIN;
import static combis.hackathon.data.api.APIConstants.PATH_PLAN;
import static combis.hackathon.data.api.APIConstants.PATH_PLANS;
import static combis.hackathon.data.api.APIConstants.PATH_TRANSPORT;

public interface TemplateAPI {

    @POST(PATH_LOGIN)
    Single<List<LoginResponse>> login(@Body UserInformation userInformation);

    @POST(PATH_IMAGE)
    Single<UploadImageResponse> uploadImage(@Body ImageRequest imageRequest);

    @GET(PATH_PLANS)
    Single<List<PlansResponse>> getUserPlans(@Path("id") long id);

    @GET(PATH_PLAN)
    Single<List<Aktivnost>> getUserActivities(@Path("id") long id);

    @GET(PATH_TRANSPORT)
    Single<List<Transport>> getTransport(@Path("id") long id);
}
