package ph.coreproc.android.devcup.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;

import org.apmem.tools.layouts.FlowLayout;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.models.Profession;
import ph.coreproc.android.devcup.models.Request;
import ph.coreproc.android.devcup.rest.RestClient;
import ph.coreproc.android.devcup.rest.Session;
import ph.coreproc.android.devcup.rest.models.LoginResponse;
import ph.coreproc.android.devcup.rest.models.RequestResponseGet;
import ph.coreproc.android.devcup.utils.DummyDataUtil;
import ph.coreproc.android.devcup.utils.ModelUtil;
import ph.coreproc.android.devcup.utils.UiUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 8/15/15.
 */
public class CreateRequestActivity extends BaseActivity {

    public static final String TAG = "CreateRequestActivity";

    @InjectView(R.id.etSubject)
    EditText mEtSubject;

    @InjectView(R.id.etMinimumOffer)
    EditText mEtMinimumOffer;

    @InjectView(R.id.etMaximumOffer)
    EditText mEtMaximumOffer;

    @InjectView(R.id.etDescription)
    EditText mEtDescription;

    @InjectView(R.id.tvAddTag)
    TextView mTvAddTag;

    @InjectView(R.id.tagsContainer)
    FlowLayout mTagsContainer;

    @InjectView(R.id.rootViewAddImage)
    View mRootViewAddImage;

    @InjectView(R.id.imagesContainer)
    FlowLayout mImagesContainer;

    Request mRequest;

    List<Bitmap> mBitmaps;


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

        mRequest = new Request();
        mRequest.tags = new ArrayList<>();

        mBitmaps = new ArrayList<>();
    }

    @OnClick(R.id.tvAddTag)
    public void addTag() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(mContext);
        builderSingle.setTitle("Tags");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                mContext,
                android.R.layout.select_dialog_item);

        for (Profession profession : DummyDataUtil.getProfessions()) {
            arrayAdapter.add(profession.name);
        }

        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = arrayAdapter.getItem(which);
                        if (mRequest.tags.contains(tag)) {
                            // tag already on list
                        } else {
                            mRequest.tags.add(arrayAdapter.getItem(which));
                            refreshTagList();
                        }
                    }
                });

        builderSingle.show();
    }

    private void refreshTagList() {
        mTagsContainer.removeAllViews();
        for (String tag : mRequest.tags) {
            View tagView = LayoutInflater.from(mContext).inflate(R.layout.layout_tag, null, false);

            final TextView tvTagName = (TextView) tagView.findViewById(R.id.tvTagName);
            TextView tvRemove = (TextView) tagView.findViewById(R.id.tvRemove);

            tvTagName.setText(tag);
            tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRequest.tags.remove(tvTagName.getText().toString());
                    refreshTagList();
                }
            });

            mTagsContainer.addView(tagView);
        }
        mTagsContainer.addView(mTvAddTag);
    }

    @OnClick(R.id.tvAddImage)
    public void addImage() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                try {
                    FileOutputStream out = new FileOutputStream("filename");
                    photo.compress(Bitmap.CompressFormat.JPEG, 90, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mBitmaps.add(photo);
                refreshImageList();
            } catch (Exception e) {
                // no image captured
            }
        }
    }

    private void refreshImageList() {
        mImagesContainer.removeAllViews();
        for (final Bitmap bitmap : mBitmaps) {
            View cardImage = LayoutInflater.from(mContext)
                    .inflate(R.layout.card_request_image, null, false);

            CardView card = (CardView) cardImage.findViewById(R.id.card);
            SelectableRoundedImageView ivImage = (SelectableRoundedImageView)
                    cardImage.findViewById(R.id.ivImage);
            TextView tvRemove = (TextView) cardImage.findViewById(R.id.tvRemove);

            card.setPreventCornerOverlap(false);

            ivImage.setImageBitmap(bitmap);
            tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBitmaps.remove(bitmap);
                    refreshImageList();
                }
            });

            mImagesContainer.addView(cardImage);
        }
        mImagesContainer.addView(mRootViewAddImage);
    }


    @OnClick(R.id.btnSubmit)
    public void submitRequest() {
        mRequest.subject = mEtSubject.getText().toString();
        mRequest.rangeMin = Double.parseDouble(mEtMinimumOffer.getText().toString());
        mRequest.rangeMax = Double.parseDouble(mEtMaximumOffer.getText().toString());
        mRequest.description = mEtDescription.getText().toString();

        final ProgressDialog progressDialog = UiUtil.getProgressDialog(mContext, "Please wait...");
        progressDialog.show();

        RestClient.getAPIService().createRequest(
                Session.getInstance().getApiKey(),
                mRequest,
                new Callback<RequestResponseGet>() {
                    @Override
                    public void success(RequestResponseGet requestResponseGet, Response response) {
                        Log.i(TAG, "RequestResponse = " + ModelUtil.toJsonString(requestResponseGet));
                        Intent intent = new Intent(mContext, CustomerHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        CreateRequestActivity.this.finish();
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
