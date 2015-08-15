package ph.coreproc.android.devcup.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.adapters.RVProposalsAdapter;
import ph.coreproc.android.devcup.models.Proposal;
import ph.coreproc.android.devcup.utils.UiUtil;

/**
 * Created by johneris on 8/16/15.
 */
public class ProposalsFragment extends Fragment {

    public static final String TAG = "ProposalsFragment";

    public Context mContext;

    @InjectView(R.id.rvProposals)
    RecyclerView mRvProposals;

    List<Proposal> mProposals;
    RVProposalsAdapter mProposalsAdapter;

    public static ProposalsFragment newInstance() {
        ProposalsFragment proposalsFragment = new ProposalsFragment();
        return proposalsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proposals, container, false);

        ButterKnife.inject(this, view);
        initialize();

        mContext = getActivity();

        return view;
    }

    private void initialize() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);

        mProposals = new ArrayList<>();
        mRvProposals.setLayoutManager(llm);

        mProposalsAdapter = new RVProposalsAdapter(mContext, mProposals);
        mRvProposals.setAdapter(mProposalsAdapter);

//        getProposals();
    }

    private void getProposals() {

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

//        RestClient.getAPIService().getRequests(
//                Session.getInstance().getApiKey(),
//                new Callback<RequestResponsePost>() {
//                    @Override
//                    public void success(RequestResponsePost requestResponsePost, Response response) {
//                        Log.i(TAG, "RequestRequest = " + ModelUtil.toJsonString(requestResponsePost));
//                        mProposals = requestResponsePost.requests;
//                        mProposalsAdapter.changeData(mProposals);
//                        progressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        try {
//                            LoginResponse loginResponse = (LoginResponse) error.getBodyAs(LoginResponse.class);
//                        } catch (Exception e) {
//                        }
//                        progressDialog.dismiss();
//                    }
//                }
//        );

    }


}
