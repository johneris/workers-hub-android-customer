package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ph.coreproc.android.devcup.models.Proposal;

/**
 * Created by johneris on 8/16/15.
 */
public class RequestProposalResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public List<Proposal> proposals;

//    public static class RequestProposal {
//
//        @SerializedName("status")
//        public String status;
//
//        @SerializedName("request")
//        public Request request;
//
//        @SerializedName("message")
//        public String message;
//
//        @SerializedName("cost")
//        public double cost;
//
//    }
}


