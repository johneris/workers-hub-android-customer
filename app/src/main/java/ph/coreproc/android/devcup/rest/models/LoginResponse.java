package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 8/15/15.
 */
public class LoginResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}
