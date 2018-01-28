import java.util.*;

public class MapUtils {

    public static Map<String, Integer> sortMap(Map<String, Integer> unsortedMap, final boolean isAscending) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        // Sorting the list based on keys
        list.sort(Comparator.comparing(Map.Entry::getKey));

        // Sorting the list based on values
        list.sort((o1, o2) -> {
            if (isAscending) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list)
            sortedMap.put(entry.getKey(), entry.getValue());

        return sortedMap;
    }

}
