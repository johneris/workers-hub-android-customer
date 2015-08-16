package ph.coreproc.android.devcup.models;

/**
 * Created by johneris on 8/16/15.
 */
public enum RequestStatus {

    OPEN, ACCEPTED, CLOSED;

    @Override
    public String toString() {
        switch (this) {
            case OPEN: return "OPEN";
            case ACCEPTED: return "ACCEPTED";
            case CLOSED: return "CLOSED";
            default: return "IMPOSSIBLE!";
        }
    }
}
