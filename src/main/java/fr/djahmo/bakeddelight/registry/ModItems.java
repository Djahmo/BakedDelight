package fr.djahmo.bakeddelight.registry;

import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.custom.item.ChefSuitItem;
import fr.djahmo.bakeddelight.custom.item.FoodItem;
import fr.djahmo.bakeddelight.utils.FoodValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BakedDelight.MODID);
    public static final RegistryObject<Item> ROLLING_PIN;
    public static final RegistryObject<Item> GROUND_MEAT, COOKED_GROUND_MEAT, LASAGNA_PASTA, SLICED_POTATO, CHEESE, BACON_BITS, COOKED_BACON_BITS, BOLOGNESE, BACON_CREAM, LASAGNA_SLICE, GRATIN_SLICE;
    public static final RegistryObject<ArmorItem> CHEFS_HAT, CHEFS_JACKET, CHEFS_PANTS, CHEFS_SHOES;
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    
    static {
        // Chef Suit
        CHEFS_HAT = ITEMS.register("chefs_hat", () -> new ChefSuitItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
        CHEFS_JACKET = ITEMS.register("chefs_jacket", () -> new ChefSuitItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
        CHEFS_PANTS = ITEMS.register("chefs_pants", () -> new ChefSuitItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
        CHEFS_SHOES = ITEMS.register("chefs_shoes", () -> new ChefSuitItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));

        // Item
        ROLLING_PIN = ITEMS.register("rolling_pin", () -> new SwordItem(Tiers.WOOD, 2, -2.4F, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
        // Food
        GROUND_MEAT = ITEMS.register("ground_meat", () -> new FoodItem(FoodValues.GROUND_MEAT));
        COOKED_GROUND_MEAT = ITEMS.register("cooked_ground_meat", () -> new FoodItem(FoodValues.COOKED_GROUND_MEAT));
        LASAGNA_PASTA = ITEMS.register("lasagna_pasta", () -> new FoodItem(FoodValues.LASAGNA_PASTA));
        SLICED_POTATO = ITEMS.register("sliced_potato", () -> new FoodItem(FoodValues.SLICED_POTATO));
        CHEESE = ITEMS.register("cheese", () -> new FoodItem(FoodValues.CHEESE));
        BACON_BITS = ITEMS.register("bacon_bits", () -> new FoodItem(FoodValues.BACON_BITS));
        COOKED_BACON_BITS = ITEMS.register("cooked_bacon_bits", () -> new FoodItem(FoodValues.COOKED_BACON_BITS));
        BOLOGNESE = ITEMS.register("bolognese", () -> new FoodItem(FoodValues.BOLOGNESE, Items.BOWL));
        BACON_CREAM = ITEMS.register("bacon_cream", () -> new FoodItem(FoodValues.BACON_CREAM));

        LASAGNA_SLICE = ITEMS.register("lasagna_slice", () -> new FoodItem(FoodValues.LASAGNA_SLICE));
        GRATIN_SLICE = ITEMS.register("gratin_slice", () -> new FoodItem(FoodValues.GRATIN_SLICE));
    }
}
