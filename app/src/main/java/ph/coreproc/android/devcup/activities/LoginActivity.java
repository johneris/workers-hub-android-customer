package ph.coreproc.android.devcup.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.UserType;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.LoginRequest;
import ph.coreproc.android.devcup.rest.models.LoginResponse;
import ph.coreproc.android.devcup.utils.ModelUtil;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

//        mEtUsername.setText("test_worker_1");
//        mEtPassword.setText("w1");
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        final String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        final String type = mRbtnCustomer.isChecked() ?
                UserType.CUSTOMER.toString() : UserType.WORKER.toString();

        final LoginRequest loginRequest = new LoginRequest(username, password, type);

        Log.i(TAG, "LoginRequest = " + ModelUtil.toJsonString(loginRequest));

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Logging in. Please wait.");
        progressDialog.show();

        RestClient.getAPIService().login(loginRequest, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                Log.i(TAG, "LoginResponse = " + ModelUtil.toJsonString(loginResponse));

                Session.getInstance().setUserID(loginResponse.message);
                Session.getInstance().setApiKey(loginResponse.message);
                Session.getInstance().setUserType(
                        UserType.CUSTOMER.toString().equals(type) ?
                                UserType.CUSTOMER : UserType.WORKER
                );

                progressDialog.dismiss();

                if(Session.getInstance().getUserType() == UserType.CUSTOMER) {
                    startActivity(CustomerHomeActivity.newIntent(mContext));
                } else {
                    startActivity(WorkerHomeActivity.newIntent(mContext));
                }
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
        });

    }

}
