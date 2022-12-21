package crud.factory_utills;

import java.util.ArrayList;
import java.util.List;

public class FactoryBase {
    private static final List<Factory> values = new ArrayList<>();

    private FactoryBase() {}

    public static void addFactory(Factory factory) {
        values.add(factory);
    }

    public static void removeFactory(String id) {
        List<Factory> list = values.stream().filter(e -> e.getId().equals(id)).toList();
        if (list.size() != 0) {
            values.remove(list.get(0));
        }
    }

    public static List<Factory> getValues() {
        return values;
    }

    public static Factory getFactory(String id) {
        List<Factory> list = values.stream().filter(e -> e.getId().equals(id)).toList();
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
