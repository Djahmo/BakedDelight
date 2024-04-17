package net.djahmo.bakeddelight.custom.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BackingDishTypeCollection {
    private static final Map<String, Integer> uncookedDishTypes = new HashMap<>();
    private static final Map<String, Integer> cookedDishTypes = new HashMap<>();
    private static final Set<ItemLike> cookedDishItems = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void registerDishType(String name, Integer id) {
        uncookedDishTypes.put("un" + name, id);
        cookedDishTypes.put(name, id);
    }

    public static Integer getDishId(String name) {
        return uncookedDishTypes.get(name);
    }

    public static boolean containsItem(Item item) {
        return cookedDishItems.contains(item);
    }

    public static void initialiseCookedDishItems() {
        cookedDishItems.clear();
        cookedDishTypes.keySet().forEach(dishName -> {
            Item dishItem = ForgeRegistries.ITEMS.getValue(new net.minecraft.resources.ResourceLocation("bakeddelight", dishName));
            if (dishItem != null) {
                cookedDishItems.add(dishItem);
            }
        });
    }
}
