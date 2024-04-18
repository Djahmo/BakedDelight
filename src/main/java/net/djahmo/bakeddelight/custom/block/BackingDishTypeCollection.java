package net.djahmo.bakeddelight.custom.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BackingDishTypeCollection {
    private static final Map<String, Integer> uncookedDishTypes = new HashMap<>();
    private static final Map<String, Integer> cookedDishTypes = new HashMap<>();
    private static final Map<String, Map<Integer, String>> dishRecipes = new HashMap<>();
    private static final Set<ItemLike> cookedDishItems = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void registerDishType(String name, Integer id) {
        uncookedDishTypes.put("un" + name, id);
        cookedDishTypes.put(name, id);
        dishRecipes.put("un" + name, new HashMap<>());
    }

    public static Integer getDishId(String name) {
        return uncookedDishTypes.get(name);
    }

    public static boolean containsItem(Item item) {
        return cookedDishItems.contains(item);
    }

    public static void registerDishRecipeIngredient(String dishName, Integer slice, String ingredient) {
        dishRecipes.get(dishName).put(slice, ingredient);
    }

    public static String getDishRecipeIngredient(String dishName, Integer slice) {
        return dishRecipes.get(dishName).get(slice);
    }

    public static void initialiseCookedDishItems() {
        cookedDishItems.clear();
        cookedDishTypes.keySet().forEach(dishName -> {
            Item dishItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation("bakeddelight", dishName));
            if (dishItem != null) {
                cookedDishItems.add(dishItem);
            }
        });
    }
}
