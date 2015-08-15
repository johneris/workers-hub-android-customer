package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 8/15/15.
 */
public class LoginRequest {

    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("type")
    public String type;

    public LoginRequest(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

}
