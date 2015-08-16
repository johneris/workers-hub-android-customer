package ph.coreproc.android.devcup.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Review;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.MyReviewsResponse;
import ph.coreproc.android.devcup.utils.ModelUtil;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/16/15.
 */
public class MyReviewsActivity extends BaseActivity {

    public static final String TAG = "MyReviewsActivity";

    @InjectView(R.id.reviewContainer)
    LinearLayout mReviewContainer;

    List<Review> reviews;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MyReviewsActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_reviews;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getReviews();
    }

    private void getReviews() {
        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

        RestClient.getAPIService().getMyReviews(
                Session.getInstance().getApiKey(),
                new Callback<MyReviewsResponse>() {
                    @Override
                    public void success(MyReviewsResponse myReviewsResponse, Response response) {
                        reviews = myReviewsResponse.reviews;
                        Log.i(TAG, "My Reviews = " + ModelUtil.toJsonString(myReviewsResponse));
                        refreshReviews();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                    }
                }
        );
    }

    private void refreshReviews() {
        mReviewContainer.removeAllViews();
        for(Review review : reviews) {
            View reviewView = LayoutInflater.from(mContext).inflate(R.layout.card_review, null, false);
            RatingBar ratingBar = (RatingBar) reviewView.findViewById(R.id.ratingBar);
            TextView tvMessage = (TextView) reviewView.findViewById(R.id.tvMessage);

            ratingBar.setRating(review.starRating);
            tvMessage.setText(review.message);

            mReviewContainer.addView(reviewView);
        }

    }


}
