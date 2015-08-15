package ph.coreproc.android.devcup.utils;

import java.util.ArrayList;
import java.util.List;

import ph.coreproc.android.devcup.models.Profession;
import ph.coreproc.android.devcup.models.Request;

/**
 * Created by johneris on 8/15/15.
 */
public class DummyDataUtil {

    public static List<Request> getRequests() {
        List<Request> requests = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Request request = new Request();
            request.subject = "Needs someone for tonight";
            request.rangeMin = 1000;
            request.rangeMax = 3000;
            request.description = "Sometimes dummy data is so awesome that when you start reading, you won't be able" +
                    " to stop though you know that all of this is some weird shit made up by a programmer that wants you" +
                    " to keep reading until your tired of it.";

            List<String> tags = new ArrayList<String>();
            tags.add("Short hair");
            tags.add("Long eyelids");
            request.tags = tags;

            requests.add(request);
        }

        return requests;
    }


    public static List<Profession> getProfessions() {
        List<Profession> professions = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            professions.add(new Profession("Plumber" + i));
        }
        return professions;
    }

}
