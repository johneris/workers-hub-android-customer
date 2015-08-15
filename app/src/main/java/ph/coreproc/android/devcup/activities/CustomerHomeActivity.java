package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.adapters.RVRequestAdapter;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.utils.DummyDataUtil;

/**
 * Created by johneris on 8/15/15.
 */
public class CustomerHomeActivity extends BaseActivity {

    @InjectView(R.id.rvRequests)
    RecyclerView mRvRequests;

    List<Request> mRequests;


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

        mRequests = DummyDataUtil.getRequests();
        mRvRequests.setLayoutManager(llm);

        RVRequestAdapter rvRequestAdapter = new RVRequestAdapter(mContext, mRequests);
        mRvRequests.setAdapter(rvRequestAdapter);
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

    }

    private void actionLogout() {
        Session.resetSession();
        finish();
    }

}
