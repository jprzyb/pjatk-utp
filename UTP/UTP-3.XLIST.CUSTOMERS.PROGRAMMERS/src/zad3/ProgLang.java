package zad3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {

    private Map<String, List<String>> langsMap = new HashMap<>();
    private Map<String, List<String>> progsMap = new HashMap<>();

    public ProgLang(String fname) {
        try {
            Files.readAllLines(Paths.get(fname)).forEach(line -> {
                String[] data = line.split("\t");

                String language = data[0];

                List<String> names = new ArrayList<>(Arrays.asList(data).subList(1, data.length));

                langsMap.put(language, names);

                names.forEach(name -> {
                    if (progsMap.containsKey(name)) {
                        progsMap.get(name).add(language);
                    } else {
                        List<String> languages = new ArrayList<>();
                        languages.add(language);
                        progsMap.put(name, languages);
                    }
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, List<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        return sortByNum(langsMap);
    }

    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        return sortByNum(progsMap);
    }

    private Map<String, List<String>> sortByNum(Map<String, List<String>> map) {
        return sorted(map, (l1, l2) -> {
            int compare = Integer.compare(l1.getValue().size(), l2.getValue().size()) * -1;

            if (compare == 0) {
                return l1.getKey().compareToIgnoreCase(l2.getKey());
            } else return compare;
        });
    }

    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        return filtered(progsMap, p -> p.getValue().size() > i);
    }

    public static <T, K> Map<T, List<K>> sorted(Map<T, List<K>> map, Comparator<Map.Entry<T, List<K>>> comparator) {
        LinkedHashMap<T, List<K>> sortedMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(comparator)
                .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

        return sortedMap;
    }

    public static <T, K> LinkedHashMap<T, List<K>> filtered(Map<T, List<K>> map, Predicate<Map.Entry<T, List<K>>> predicate) {
        LinkedHashMap<T, List<K>> filteredMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .filter(predicate)
                .forEach(entry -> filteredMap.put(entry.getKey(), entry.getValue()));

        return filteredMap;
    }
}
