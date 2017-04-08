package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.request.ImageRequest;
import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.api.models.response.PlansResponse;
import combis.hackathon.data.api.models.response.UploadImageResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static combis.hackathon.data.api.APIConstants.PATH_IMAGE;
import static combis.hackathon.data.api.APIConstants.PATH_LOGIN;
import static combis.hackathon.data.api.APIConstants.PATH_PLANS;

public interface TemplateAPI {


    @POST(PATH_LOGIN)
    Single<List<LoginResponse>> login(@Body UserInformation userInformation);

    @POST(PATH_IMAGE)
    Single<UploadImageResponse> uploadImage(@Body ImageRequest imageRequest);

    @GET(PATH_PLANS)
    Single<List<PlansResponse>> getUserPlans();


//    @Headers({CONTENT_TYPE_HEADER, ACCEPT_HEADER})
//    @HTTP(method = "DELETE", path = PATH_CLAIM_DEVICE, hasBody = true)
//    Observable<Response<Void>> returnDevice(@Header("Authorization") String authorization, @Body User user,
//            @Path("person_id") long userId);
}
