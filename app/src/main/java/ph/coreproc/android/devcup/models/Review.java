package ph.coreproc.android.devcup.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 8/16/15.
 */
public class Review {

    @SerializedName("rating")
    public int starRating;

    @SerializedName("message")
    public String message;

}
