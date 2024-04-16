package net.djahmo.bakeddelight.custom.block;

import java.util.HashMap;
import java.util.Map;

public class BackingDishTypeCollection {
    private static final Map<String, Integer> dishTypes = new HashMap<>();

    public static void registerDishType(String name, Integer id) {
        dishTypes.put(name, id);
    }

    public static Integer getDishId(String name) {
        return dishTypes.get(name);
    }

}
