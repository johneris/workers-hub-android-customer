package ph.coreproc.android.devcup.activities;

import android.app.ProgressDialog;
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
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.RequestProposalResponse;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/16/15.
 */
public class ProposalListActivity extends BaseActivity {

    public static final String ARGS_REQUEST_ID = "ARGS_REQUEST_ID";

    @InjectView(R.id.rvProposals)
    RecyclerView mRvProposals;

    List<Proposal> mProposals;
    RVProposalsAdapter mProposalsAdapter;

    String requestID;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_proposal_list;
    }

    public static Intent newIntent(Context context, String requestID) {
        Intent intent = new Intent(context, ProposalListActivity.class);
        intent.putExtra(ARGS_REQUEST_ID, requestID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        requestID = bundle.getString(ARGS_REQUEST_ID);

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

        mProposalsAdapter = new RVProposalsAdapter(mContext, mProposals, requestID);
        mRvProposals.setAdapter(mProposalsAdapter);

        getRequestProposals();
    }

    private void getRequestProposals() {

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

        RestClient.getAPIService().getRequestProposals(
                Session.getInstance().getApiKey(),
                requestID,
                new Callback<RequestProposalResponse>() {
                    @Override
                    public void success(RequestProposalResponse requestProposalResponse, Response response) {
                        mProposals = requestProposalResponse.proposals;
                        mProposalsAdapter.changeData(mProposals);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UiUtil.showMessageDialog(getSupportFragmentManager(), error.getMessage());
                        progressDialog.dismiss();
                    }
                }
        );

    }


}
