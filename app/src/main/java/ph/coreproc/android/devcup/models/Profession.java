package ph.coreproc.android.devcup.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 8/15/15.
 */
public class Profession {

    @SerializedName("name")
    public String name;

    public Profession(String name) {
        this.name = name;
    }

}
