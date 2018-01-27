import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtils {

    public static Map<String, Integer> sortMap(Map<String, Integer> unsortMap, final boolean isAscending) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

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
