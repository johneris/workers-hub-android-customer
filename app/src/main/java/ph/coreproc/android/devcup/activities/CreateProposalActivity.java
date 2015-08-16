package ph.coreproc.android.devcup.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Proposal;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/16/15.
 */
public class CreateProposalActivity extends BaseActivity {


    public static final String ARGS_REQUEST_ID = "ARGS_REQUEST_ID";

    @InjectView(R.id.etCost)
    EditText mEtCost;

    @InjectView(R.id.etMessage)
    EditText mEtMessage;

    String requestID;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_proposal;
    }

    public static Intent newIntent(Context context, String requestID) {
        Intent intent =  new Intent(context, CreateProposalActivity.class);
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
        mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.btnSubmit)
    public void submitProposal() {

        String cost = mEtCost.getText().toString();
        String message = mEtMessage.getText().toString();

        Proposal proposal = new Proposal();
        proposal.cost = Double.parseDouble(cost);
        proposal.message = message;

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

        RestClient.getAPIService().createProposal(
                Session.getInstance().getApiKey(),
                requestID,
                proposal,
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        progressDialog.dismiss();
                        UiUtil.showMessageDialog(getSupportFragmentManager(), "Submit Bid successful");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        UiUtil.showMessageDialog(getSupportFragmentManager(), error.getMessage());
                    }
                }
        );
    }

}
