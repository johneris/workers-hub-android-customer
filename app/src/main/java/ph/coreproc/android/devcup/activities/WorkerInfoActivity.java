package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Review;
import ph.coreproc.android.devcup.models.Worker;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.WorkerInfoResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/16/15.
 */
public class WorkerInfoActivity extends BaseActivity {

    public static final String ARGS_WORKER_ID = "ARGS_WORKER_ID";

    @InjectView(R.id.tvUsername)
    TextView mTvUsername;

    @InjectView(R.id.tvName)
    TextView mTvName;

    @InjectView(R.id.tvEmail)
    TextView mTvEmail;

    @InjectView(R.id.tvMobileNumber)
    TextView mTvMobileNumber;

    @InjectView(R.id.reviewContainer)
    LinearLayout mReviewContainer;

    String mWorkerID;

    Worker mWorker;


    public static Intent newIntent(Context context, String workerID) {
        Intent intent = new Intent(context, WorkerInfoActivity.class);
        intent.putExtra(ARGS_WORKER_ID, workerID);
        return intent;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_worker_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        mWorkerID = bundle.getString(ARGS_WORKER_ID);

        initialize();
    }

    private void initialize() {
        mWorker = new Worker();
    }

    private void refreshWorkerInfo() {
        mTvUsername.setText(mWorker.userName);
        mTvName.setText("" + mWorker.firstName + " " + mWorker.lastName);
        mTvEmail.setText(mWorker.email);
        mTvMobileNumber.setText(mWorker.mobile);

        mReviewContainer.removeAllViews();
        for(Review review : mWorker.reviews) {
            View reviewView = LayoutInflater.from(mContext).inflate(R.layout.card_review, null, false);
            RatingBar ratingBar = (RatingBar) reviewView.findViewById(R.id.ratingBar);
            TextView tvMessage = (TextView) reviewView.findViewById(R.id.tvMessage);

            ratingBar.setRating(review.starRating);
            tvMessage.setText(review.message);

            mReviewContainer.addView(reviewView);
        }

    }

    private void getWorkerInfo() {
        RestClient.getAPIService().getWorkerInfo(
                Session.getInstance().getApiKey(),
                mWorkerID,
                new Callback<WorkerInfoResponse>() {
                    @Override
                    public void success(WorkerInfoResponse workerInfoResponse, Response response) {
                        mWorker = workerInfoResponse.worker;
                        refreshWorkerInfo();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );
    }

}
