package ph.coreproc.android.devcup.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.UserType;
import ph.coreproc.android.devcup.rest.models.LoginRequest;
import ph.coreproc.android.devcup.utils.ModelUtil;

/**
 * Created by johneris on 6/16/2015.
 */
public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    @InjectView(R.id.etUsername)
    EditText mEtUsername;

    @InjectView(R.id.etPassword)
    EditText mEtPassword;

    @InjectView(R.id.rbtnCustomer)
    RadioButton mRbtnCustomer;

    @InjectView(R.id.rbtnWorker)
    RadioButton mRbtnWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        String type = mRbtnCustomer.isSelected() ?
                UserType.CUSTOMER.toString() : UserType.WORKER.toString();

        final LoginRequest loginRequest = new LoginRequest(username, password, type);

        Log.i(TAG, "LoginRequest = " + ModelUtil.toJsonString(loginRequest));

//        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Logging in. Please wait.");
//        progressDialog.show();
//
//        RestClient.getAPIService().login(loginRequest, new Callback<LoginResponse>() {
//            @Override
//            public void success(LoginResponse loginResponse, Response response) {
//                Log.i(TAG, "LoginResponse = " + ModelUtil.toJsonString(loginResponse));
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                UiUtil.showMessageDialog(getSupportFragmentManager(), error.getMessage());
//                progressDialog.dismiss();
                startActivity(CustomerHomeActivity.newIntent(mContext));
//            }
//        });

    }

}
