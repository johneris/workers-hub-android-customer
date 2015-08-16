package ph.coreproc.android.devcup.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ph.coreproc.android.devcup.models.Review;

/**
 * Created by johneris on 8/16/15.
 */
public class MyReviewsResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public List<Review> reviews;

}
