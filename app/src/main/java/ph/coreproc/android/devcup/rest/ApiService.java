package ph.coreproc.android.devcup.rest;

import java.util.List;

import ph.coreproc.android.devcup.models.Contributor;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.rest.models.LoginRequest;
import ph.coreproc.android.devcup.rest.models.LoginResponse;
import ph.coreproc.android.devcup.rest.models.ProfessionResponse;
import ph.coreproc.android.devcup.rest.models.RequestResponseGet;
import ph.coreproc.android.devcup.rest.models.RequestResponsePost;
import ph.coreproc.android.devcup.rest.models.WorkerFeedResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by johneris on 6/5/2015.
 */
public interface ApiService {

//    @GET("")
//    void getContributors(@Header("X-Authorization") String authorization, Callback<Contributor> callback);

//    @GET("")
//    void getContributors(Callback<com.google.gson.JsonObject> callback);

//    @POST("/users/new")
//    void createUser(@Body User user, Callback<User> cb);

//    @POST("/users/new")
//    void createUser(@Body TypedJsonString user, Callback<com.google.gson.JsonObject> cb);

    // MyModel m = new MyModel("some");
    // JsonObject jsonObject = ModelUtil.toJsonObject(m);
    // jsonObject.addProperty("key", "value you want to add");
    // RestClient.getApiService().myMethod(new TypedJsonString(jsonObject.toString()),
    //      new Callback<com.google.gson.JsonObject> callback);

    @GET("/repos/{user}/{repository}/contributors")
    void getContributors(
            @Path("user") String user,
            @Path("repository") String repository,
            Callback<List<Contributor>> callback
    );

    @POST("/api/v1/login")
    void login(@Body LoginRequest loginRequest, Callback<LoginResponse> callback);

    @POST("/api/v1/requests")
    void createRequest(
            @Header("X-Authorization") String authorization,
            @Body Request request,
            Callback<RequestResponsePost> callback
    );

    @GET("/api/v1/requests")
    void getRequests(
            @Header("X-Authorization") String authorization,
            Callback<RequestResponseGet> callback
    );

    @GET("/api/v1/professions")
    void getProfessions(
            Callback<ProfessionResponse> callback
    );

    @GET("/api/v1/worker/requests")
    void getWorkerFeed(
            @Header("X-Authorization") String authorization,
            Callback<WorkerFeedResponse> callback
    );

    @POST("/api/v1/")


}
