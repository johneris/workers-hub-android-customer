package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Review;

/**
 * Created by johneris on 8/16/15.
 */
public class CreateReviewActivity extends BaseActivity {

    @InjectView(R.id.tvName)
    TextView mTvName;

    @InjectView(R.id.ratingBar)
    RatingBar mRatingBar;

    @InjectView(R.id.etMessage)
    EditText mEtMessage;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_create_review;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreateReviewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void submitReview() {

        Review review = new Review();
        review.starRating = Integer.parseInt(mRatingBar.getRating() + "");
        review.message = mEtMessage.getText().toString();

        // http here
    }

}
