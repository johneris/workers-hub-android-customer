package ph.coreproc.android.devcup.rest;

import ph.coreproc.android.devcup.models.UserType;

/**
 * Created by johneris on 8/15/15.
 */
public class Session {

    private String mUserID;
    private String mApiKey;
    private UserType mUserType;

    private static Session session;

    private Session() {
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public static void resetSession() {
        session = new Session();
    }

    public String getUserID() {
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public UserType getUserType() {
        return mUserType;
    }

    public void setUserType(UserType userType) {
        mUserType = userType;
    }
}
