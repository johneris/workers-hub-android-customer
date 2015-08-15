package ph.coreproc.android.devcup.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ph.coreproc.android.devcup.fragments.AcceptedRequestsFragment;
import ph.coreproc.android.devcup.fragments.ExampleFragment;
import ph.coreproc.android.devcup.fragments.FeedsFragment;
import ph.coreproc.android.devcup.fragments.ProposalsFragment;

/**
 * Created by johneris on 8/16/15.
 */
public class PagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Context mContext;

    public PagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FeedsFragment.newInstance();
            case 1:
                return ProposalsFragment.newInstance();
            case 2:
                return AcceptedRequestsFragment.newInstance();
        }
        return ExampleFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "Title not set";
        switch (position) {
            case 0:
                title = "Feeds";
                break;
            case 1:
                title = "Bids";
                break;
            case 2:
                title = "Accepted \nRequests";
                break;
        }
        return title;
    }
}
