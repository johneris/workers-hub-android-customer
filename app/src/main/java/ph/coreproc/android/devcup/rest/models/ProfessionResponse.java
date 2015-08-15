package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by johneris on 8/16/15.
 */
public class ProfessionResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public List<String> tags;

}
