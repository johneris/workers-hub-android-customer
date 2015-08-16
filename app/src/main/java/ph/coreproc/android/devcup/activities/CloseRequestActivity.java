package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Review;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/16/15.
 */
public class CloseRequestActivity extends BaseActivity {

    public static final String ARGS_REQUEST_ID = "ARGS_REQUEST_ID";

    @InjectView(R.id.ratingBar)
    RatingBar mRatingBar;

    @InjectView(R.id.etReview)
    EditText mEtReview;

    String mRequestID;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_close_request;
    }

    public static Intent newIntent(Context context, String requestID) {
        Intent intent = new Intent(context, CloseRequestActivity.class);
        intent.putExtra(ARGS_REQUEST_ID, requestID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        mRequestID = bundle.getString(ARGS_REQUEST_ID);
        initialize();
    }

    private void initialize() {
        mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.btnSubmit)
    public void closeRequest() {
        int rating = (int) mRatingBar.getRating();
        String strReview = mEtReview.getText().toString();

        Review review = new Review();
        review.starRating = rating;
        review.message = strReview;

        RestClient.getAPIService().writeReview(
                Session.getInstance().getApiKey(),
                mRequestID,
                review,
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        UiUtil.showMessageDialog(getSupportFragmentManager(), "Request successfully closed.");
                        CloseRequestActivity.this.finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UiUtil.showMessageDialog(getSupportFragmentManager(), "Failed to close request and submit review.");
                        CloseRequestActivity.this.finish();
                    }
                }
        );
    }

}
