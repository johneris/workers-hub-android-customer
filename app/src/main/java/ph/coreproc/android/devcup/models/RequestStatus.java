package ph.coreproc.android.devcup.models;

import android.support.annotation.ColorRes;

import ph.coreproc.android.devcup.R;

/**
 * Created by johneris on 8/16/15.
 */
public enum RequestStatus {

    OPEN, ACCEPTED, CLOSED;

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "OPEN";
            case ACCEPTED:
                return "ACCEPTED";
            case CLOSED:
                return "CLOSED";
            default:
                return "IMPOSSIBLE!";
        }
    }


    public
    @ColorRes
    int getTextColor() {
        switch (this) {
            case OPEN:
                return R.color.status_open;
            case ACCEPTED:
                return R.color.status_accepted;
            case CLOSED:
                return R.color.status_closed;
            default:
                return R.color.status_open;
        }
    }
}
