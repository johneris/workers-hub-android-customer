package ph.coreproc.android.devcup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Proposal;

/**
 * Created by johneris on 6/8/2015.
 */
public class RVProposalsAdapter extends RecyclerView.Adapter<RVProposalsAdapter.ProposalViewHolder> {

    List<Proposal> mProposals;
    private Context mContext;

    public RVProposalsAdapter(Context context, List<Proposal> proposals) {
        mContext = context;
        mProposals = proposals;
    }

    @Override
    public ProposalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_proposal, parent, false);
        ProposalViewHolder proposalViewHolder = new ProposalViewHolder(view);
        return proposalViewHolder;
    }

    @Override
    public void onBindViewHolder(ProposalViewHolder holder, int position) {
        Proposal proposal = mProposals.get(position);

        holder.mTvRange.setText(proposal.range);
        holder.mTvDescription.setText(proposal.message);
    }

    @Override
    public int getItemCount() {
        return mProposals.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void changeData(List<Proposal> proposals) {
        mProposals = proposals;
        notifyDataSetChanged();
    }

    public static class ProposalViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tvRange)
        TextView mTvRange;

        @InjectView(R.id.tvDescription)
        TextView mTvDescription;

        ProposalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
