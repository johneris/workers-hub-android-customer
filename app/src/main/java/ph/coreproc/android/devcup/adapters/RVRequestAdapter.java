package ph.coreproc.android.devcup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.utils.FormatUtil;

/**
 * Created by johneris on 6/8/2015.
 */
public class RVRequestAdapter extends RecyclerView.Adapter<RVRequestAdapter.RequestViewHolder> {

    List<Request> mRequests;
    private Context mContext;

    public RVRequestAdapter(Context context, List<Request> requests) {
        mContext = context;
        mRequests = requests;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_request, parent, false);
        RequestViewHolder requestViewHolder = new RequestViewHolder(view);
        return requestViewHolder;
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        Request request = mRequests.get(position);

        holder.mTvSubject.setText(request.subject);

        holder.mTvRange.setText(FormatUtil.toPesoFormat(request.rangeMin) +
                " - " + FormatUtil.toPesoFormat(request.rangeMax));

        holder.mTvDescription.setText(request.description);


        holder.mTagsContainer.removeAllViews();
        for (String tag : request.tags) {
            View tagView = LayoutInflater.from(mContext).inflate(R.layout.layout_tag, null, false);
            TextView tvTagName = (TextView) tagView.findViewById(R.id.tvTagName);
            TextView tvBorder = (TextView) tagView.findViewById(R.id.tvBorder);
            TextView tvRemove = (TextView) tagView.findViewById(R.id.tvRemove);

            tvBorder.setVisibility(View.GONE);
            tvRemove.setVisibility(View.GONE);
            tvTagName.setText(tag);
            holder.mTagsContainer.addView(tagView);
        }

        holder.mTvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mTvPropose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mTvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mTvProposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void changeData(List<Request> requests) {
        mRequests = requests;
        notifyDataSetChanged();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tvSubject)
        TextView mTvSubject;

        @InjectView(R.id.tvRange)
        TextView mTvRange;

        @InjectView(R.id.tvDescription)
        TextView mTvDescription;

        @InjectView(R.id.tagsContainer)
        LinearLayout mTagsContainer;

        @InjectView(R.id.imagesContainer)
        LinearLayout mImagesContainer;

        @InjectView(R.id.tvSeeMore)
        TextView mTvSeeMore;

        @InjectView(R.id.tvPropose)
        TextView mTvPropose;

        @InjectView(R.id.tvReview)
        TextView mTvReview;

        @InjectView(R.id.tvProposals)
        TextView mTvProposals;

        @InjectView(R.id.tvStatus)
        TextView mTvStatus;

        RequestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
