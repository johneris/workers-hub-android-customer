package ph.coreproc.android.devcup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.InjectView;
import ph.coreproc.android.devcup.R;
import ph.coreproc.android.devcup.adapters.PagerAdapter;
import ph.coreproc.android.devcup.rest.Session;

/**
 * Created by johneris on 8/16/15.
 */
public class WorkerHomeActivity extends BaseActivity {

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;

    @InjectView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home_worker;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, WorkerHomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        int tabTextColor = getResources().getColor(R.color.tab_title);
        mTabLayout.setTabTextColors(tabTextColor, tabTextColor);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), mContext);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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

    private void actionReviewsMenuItem() {
        startActivity(WorkerInfoActivity.newIntent(mContext, Session.getInstance().getUserID()));
    }

    private void actionLogout() {
        Session.resetSession();
        finish();
    }

}
