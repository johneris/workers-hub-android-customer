package ph.coreproc.android.devcup.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 8/16/15.
 */
public class Proposal {

    @SerializedName("id")
    public int id;

    @SerializedName("worker")
    public String worker;

    @SerializedName("worker_id")
    public int workerID;

    @SerializedName("subject")
    public String requestSubject;

    @SerializedName("status")
    public String status;

    @SerializedName("cost")
    public double cost;

    @SerializedName("message")
    public String message;

}
