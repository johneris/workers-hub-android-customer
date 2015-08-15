package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.adapters.RVProposalsAdapter;
import ph.coreproc.android.devcup.models.Proposal;

/**
 * Created by johneris on 8/16/15.
 */
public class ProposalListActivity extends BaseActivity {

    @InjectView(R.id.rvProposals)
    RecyclerView mRvProposals;

    List<Proposal> mProposals;
    RVProposalsAdapter mProposalsAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_proposal_list;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ProposalListActivity.class);
        return intent;
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

        LinearLayoutManager llm = new LinearLayoutManager(mContext);

        mProposals = new ArrayList<>();
        mRvProposals.setLayoutManager(llm);

        mProposalsAdapter = new RVProposalsAdapter(mContext, mProposals);
        mRvProposals.setAdapter(mProposalsAdapter);

//        getProposals();
    }


}
