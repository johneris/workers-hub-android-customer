package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.InjectView;
import ph.coreproc.android.devcup.R;

/**
 * Created by johneris on 8/15/15.
 */
public class CreateRequestActivity extends BaseActivity {

    @InjectView(R.id.etSubject)
    EditText mEtSubject;

    @InjectView(R.id.etMinimumOffer)
    EditText mEtMinimumOffer;

    @InjectView(R.id.etMaximumOffer)
    EditText mEtMaximumOffer;

    @InjectView(R.id.etDescription)
    EditText mEtDescription;

    @InjectView(R.id.tagsContainer)
    LinearLayout mTagsContainer;

    @InjectView(R.id.imagesContainer)
    LinearLayout mImagesContainer;



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_create_request;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreateRequestActivity.class);
        return intent;
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


}
