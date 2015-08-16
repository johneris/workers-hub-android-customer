package ph.coreproc.android.devcup.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.adapters.RVRequestAdapter;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.LoginResponse;
import ph.coreproc.android.devcup.rest.models.RequestResponseGet;
import ph.coreproc.android.devcup.utils.ModelUtil;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/15/15.
 */
public class CustomerHomeActivity extends BaseActivity {

    public static final String TAG = "CustomerHomeActivity";

    @InjectView(R.id.rvRequests)
    RecyclerView mRvRequests;

    List<Request> mRequests;
    RVRequestAdapter mRvRequestAdapter;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home_customer;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CustomerHomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);

//        mRequests = DummyDataUtil.getRequests();
        mRequests = new ArrayList<>();
        mRvRequests.setLayoutManager(llm);

        mRvRequestAdapter = new RVRequestAdapter(mContext, mRequests);
        mRvRequests.setAdapter(mRvRequestAdapter);

        getRequests();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.reviewsMenuItem:
                actionReviewsMenuItem();
                return true;
            case R.id.logoutMenuItem:
                actionLogout();
                return true;
        }

        return false;
    }

    @OnClick(R.id.fabCreateRequest)
    public void createRequest() {
        startActivity(CreateRequestActivity.newIntent(mContext));
    }


    private void actionReviewsMenuItem() {
        startActivity(MyReviewsActivity.newIntent(mContext));
    }

    private void actionLogout() {
        Session.resetSession();
        finish();
    }

    private void getRequests() {

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

        RestClient.getAPIService().getRequests(
                Session.getInstance().getApiKey(),
                new Callback<RequestResponseGet>() {
                    @Override
                    public void success(RequestResponseGet requestResponseGet, Response response) {
                        Log.i(TAG, "RequestRequest = " + ModelUtil.toJsonString(requestResponseGet));
                        mRequests = requestResponseGet.requests;
                        mRvRequestAdapter.changeData(mRequests);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            LoginResponse loginResponse = (LoginResponse) error.getBodyAs(LoginResponse.class);
                            UiUtil.showMessageDialog(getSupportFragmentManager(), loginResponse.message);
                        } catch (Exception e) {
                            UiUtil.showMessageDialog(getSupportFragmentManager(), error.getMessage());
                        }
                        progressDialog.dismiss();
                    }
                }
        );

    }

}
