package ph.coreproc.android.devcup.rest;

import java.util.List;

import ph.coreproc.android.devcup.models.Contributor;
import ph.coreproc.android.devcup.models.Proposal;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.models.Review;
import ph.coreproc.android.devcup.rest.models.LoginRequest;
import ph.coreproc.android.devcup.rest.models.LoginResponse;
import ph.coreproc.android.devcup.rest.models.MyReviewsResponse;
import ph.coreproc.android.devcup.rest.models.ProfessionResponse;
import ph.coreproc.android.devcup.rest.models.RequestProposalResponse;
import ph.coreproc.android.devcup.rest.models.RequestResponseGet;
import ph.coreproc.android.devcup.rest.models.RequestResponsePost;
import ph.coreproc.android.devcup.rest.models.WorkerFeedResponse;
import ph.coreproc.android.devcup.rest.models.WorkerInfoResponse;
import ph.coreproc.android.devcup.rest.models.WorkerProposalsResponse;
import retrofit.Callback;
import retrofit.client.Response;
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

    @GET("/api/v1/worker/proposals")
    void getWorkerProposals(
            @Header("X-Authorization") String authorization,
            Callback<WorkerProposalsResponse> callback
    );

    @GET("/api/v1/worker/accepted")
    void getWorkerAccepted(
            @Header("X-Authorization") String authorization,
            Callback<WorkerProposalsResponse> callback
    );

    @GET("/api/v1/requests/{request_id}/proposals/all")
    void getRequestProposals(
            @Header("X-Authorization") String authorization,
            @Path("request_id") String requestID,
            Callback<RequestProposalResponse> callback
    );

    @GET("/api/v1/requests/{request_id}/proposals/{proposal_id}")
    void acceptProposal(
            @Header("X-Authorization") String authorization,
            @Path("request_id") String requestID,
            @Path("proposal_id") String proposalID,
            Callback<Response> callback
    );

    @POST("/api/v1/requests/{id}/proposals")
    void createProposal(
            @Header("X-Authorization") String authorization,
            @Path("id") String requestID,
            @Body Proposal proposal,
            Callback<Response> callback
    );

    @GET("/api/v1/workers/{id}")
    void getWorkerInfo(
            @Header("X-Authorization") String authorization,
            @Path("id") String id,
            Callback<WorkerInfoResponse> callback
    );

    @POST("/api/v1/requests/{id}/review")
    void writeReviewCustomer(
            @Header("X-Authorization") String authorization,
            @Path("id") String id,
            @Body Review review,
            Callback<Response> callback
    );

    @POST("/api/v1/worker/requests/{id}/review")
    void writeReviewWorker(
            @Header("X-Authorization") String authorization,
            @Path("id") String id,
            @Body Review review,
            Callback<Response> callback
    );

    @POST("/api/v1/reviews")
    void getMyReviews(
            @Header("X-Authorization") String authorization,
            Callback<MyReviewsResponse> callback
    );

}
