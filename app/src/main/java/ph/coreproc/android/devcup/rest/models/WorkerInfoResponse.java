package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

import ph.coreproc.android.devcup.models.Worker;

/**
 * Created by johneris on 8/16/15.
 */
public class WorkerInfoResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public Worker worker;

}
