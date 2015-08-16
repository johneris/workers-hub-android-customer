package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ph.coreproc.android.devcup.models.Request;

/**
 * Created by johneris on 8/16/15.
 */
public class WorkerProposalsResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public List<RequestProposal> proposals;

    public static class RequestProposal {

        @SerializedName("status")
        public String status;

        @SerializedName("request")
        public Request request;

        @SerializedName("message")
        public String message;

        @SerializedName("cost")
        public double cost;

        @SerializedName("id")
        public int id;

        @SerializedName("subject")
        public String subject;

    }

}
