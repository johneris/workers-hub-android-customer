package ph.coreproc.android.devcup.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by johneris on 8/16/15.
 */
public class Worker {

    @SerializedName("username")
    public String userName;

    @SerializedName("first")
    public String firstName;

    @SerializedName("last")
    public String lastName;

    @SerializedName("email")
    public String email;

    @SerializedName("mobile")
    public String mobile;

    @SerializedName("reviews")
    public List<Review> reviews;

}
