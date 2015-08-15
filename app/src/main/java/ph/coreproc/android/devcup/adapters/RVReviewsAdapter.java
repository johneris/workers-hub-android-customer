package ph.coreproc.android.devcup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Review;

/**
 * Created by johneris on 6/8/2015.
 */
public class RVReviewsAdapter extends RecyclerView.Adapter<RVReviewsAdapter.ReviewViewHolder> {

    List<Review> mReviews;
    private Context mContext;

    public RVReviewsAdapter(Context context, List<Review> reviews) {
        mContext = context;
        mReviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_review, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Review review = mReviews.get(position);

        holder.mRatingBar.setRating(review.starRating);
        holder.mTvMessage.setText(review.message);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void changeData(List<Review> reviews) {
        mReviews = reviews;
        notifyDataSetChanged();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ratingBar)
        RatingBar mRatingBar;

        @InjectView(R.id.tvMessage)
        TextView mTvMessage;

        ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
