package ph.coreproc.android.devcup.activities;

import android.content.Intent;
import android.os.Bundle;

import ph.coreproc.android.devcup.R;

/**
 * Created by johneris on 6/1/2015.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

}
