package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ph.coreproc.android.devcup.models.Request;

/**
 * Created by johneris on 8/16/15.
 */
public class RequestResponseGet {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public List<Request> requests;

}
