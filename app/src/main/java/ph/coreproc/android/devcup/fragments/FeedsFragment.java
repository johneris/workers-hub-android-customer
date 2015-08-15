package ph.coreproc.android.devcup.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.adapters.RVRequestAdapter;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.WorkerFeedResponse;
import ph.coreproc.android.devcup.utils.ModelUtil;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/16/15.
 */
public class FeedsFragment extends Fragment {

    public static final String TAG = "FeedsFragment";

    public Context mContext;

    @InjectView(R.id.rvRequests)
    RecyclerView mRvRequests;

    List<Request> mRequests;
    RVRequestAdapter mRvRequestAdapter;

    public static FeedsFragment newInstance() {
        FeedsFragment feedsFragment = new FeedsFragment();
        return feedsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initialize();
    }

    private void initialize() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);

        mRequests = new ArrayList<>();
        mRvRequests.setLayoutManager(llm);

        mRvRequestAdapter = new RVRequestAdapter(mContext, mRequests);
        mRvRequests.setAdapter(mRvRequestAdapter);

        getFeeds();
    }

    private void getFeeds() {

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

        RestClient.getAPIService().getWorkerFeed(
                Session.getInstance().getApiKey(),
                new Callback<WorkerFeedResponse>() {
                    @Override
                    public void success(WorkerFeedResponse workerFeedResponse, Response response) {
                        Log.i(TAG, "WorkerFeedResponse = " + ModelUtil.toJsonString(workerFeedResponse));
                        mRequests = workerFeedResponse.requests;
                        mRvRequestAdapter.changeData(mRequests);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "WorkerFeedResponse: " + error.getMessage());
                        progressDialog.dismiss();
                    }
                }
        );

    }


}
