package ph.coreproc.android.devcup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.activities.WorkerInfoActivity;
import ph.coreproc.android.devcup.models.Proposal;
import ph.coreproc.android.devcup.models.UserType;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.utils.FormatUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 6/8/2015.
 */
public class RVProposalsAdapter extends RecyclerView.Adapter<RVProposalsAdapter.ProposalViewHolder> {

    public static final String TAG = "RVProposalsAdapter";

    List<Proposal> mProposals;
    String mRequestID;

    private Context mContext;

    public RVProposalsAdapter(Context context, List<Proposal> proposals) {
        mContext = context;
        mProposals = proposals;
        mRequestID = null;
    }

    public RVProposalsAdapter(Context context, List<Proposal> proposals, String requestID) {
        mContext = context;
        mProposals = proposals;
        mRequestID = requestID;
    }

    @Override
    public ProposalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_proposal, parent, false);
        ProposalViewHolder proposalViewHolder = new ProposalViewHolder(view);
        return proposalViewHolder;
    }

    @Override
    public void onBindViewHolder(ProposalViewHolder holder, int position) {
        final Proposal proposal = mProposals.get(position);

        if(Session.getInstance().getUserType() == UserType.CUSTOMER) {
            holder.mTvWorkerName.setText(proposal.worker);
        } else {
            holder.mTvWorkerName.setText(proposal.requestSubject);
        }

        holder.mTvCost.setText(FormatUtil.toPesoFormat(proposal.cost));
        holder.mTvDescription.setText(proposal.message);

        holder.mTvAccept.setVisibility(
                Session.getInstance().getUserType() == UserType.CUSTOMER ?
                        View.VISIBLE : View.GONE
        );
        holder.mTvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptProposal(proposal);
            }
        });

        holder.mTvWorker.setVisibility(
                Session.getInstance().getUserType() == UserType.CUSTOMER ?
                        View.VISIBLE : View.GONE
        );
        holder.mTvWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(WorkerInfoActivity.newIntent(mContext, proposal.workerID + ""));
            }
        });

    }

    private void acceptProposal(Proposal proposal) {
        RestClient.getAPIService().acceptProposal(
                Session.getInstance().getApiKey(),
                mRequestID,
                proposal.id + "",
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        Log.i(TAG, "AcceptProposal " + response.getStatus());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(TAG, "AcceptProposal " + error.getMessage());
                    }
                }
        );
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

        @InjectView(R.id.tvWorkerName)
        TextView mTvWorkerName;

        @InjectView(R.id.tvCost)
        TextView mTvCost;

        @InjectView(R.id.tvDescription)
        TextView mTvDescription;

        @InjectView(R.id.tvAccept)
        TextView mTvAccept;

        @InjectView(R.id.tvWorker)
        TextView mTvWorker;

        ProposalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
