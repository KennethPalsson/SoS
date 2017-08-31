package se.ocarina.straightonstrait.service;

import java.util.ArrayList;
import java.util.List;

public final class CollectionHelper {
    public static List<String> getNames(List<StopLocation> items) {
        List<String> result = new ArrayList<>(items.size());
        for (StopLocation item : items) {
            result.add(item.getName());
        }
        return result;
    }

    public static Departure getDeparture(List<Departure> items, String type, String name) {
        for (Departure item : items) {
            if (item.getType().equals(type) && item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static Arrival getArrival(List<Arrival> items, String type, String name) {
        for (Arrival item : items) {
            if (item.getType().equals(type) && item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
