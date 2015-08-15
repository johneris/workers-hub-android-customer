package ph.coreproc.android.devcup.models;

/**
 * Created by johneris on 8/15/15.
 */
public enum  UserType {

    CUSTOMER, WORKER;

    @Override
    public String toString() {
        switch (this) {
            case CUSTOMER: return "customer";
            case WORKER: return "worker";
            default: return "invalid user type";
        }
    }
}
