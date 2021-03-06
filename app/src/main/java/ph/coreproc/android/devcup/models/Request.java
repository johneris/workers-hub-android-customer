package ph.coreproc.android.devcup.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by johneris on 8/15/15.
 */
public class Request {

    @SerializedName("subject")
    public String subject;

    @SerializedName("range_min")
    public double rangeMin;

    @SerializedName("range_max")
    public double rangeMax;

    @SerializedName("description")
    public String description;

    @SerializedName("id")
    public int id;

    @SerializedName("status")
    public String status;

    @SerializedName("accepted_worker_id")
    public String acceptedWorkerID;

    @SerializedName("tags")
    public List<String> tags;

}
