package ph.coreproc.android.devcup.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;

/**
 * Created by johneris on 8/16/15.
 */
public class CreateProposalActivity extends BaseActivity {


    @InjectView(R.id.etCost)
    EditText mEtCost;

    @InjectView(R.id.etMessage)
    EditText mEtMessage;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_proposal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        // http here
    }

}
