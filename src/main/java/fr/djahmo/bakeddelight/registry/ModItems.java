package fr.djahmo.bakeddelight.registry;

import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.utils.FoodValues;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BakedDelight.MODID);
    public static final RegistryObject<Item> ROLLING_PIN;
    public static final RegistryObject<Item> GROUND_BEEF, GROUND_PORKCHOP, GROUND_BEEF_PORKCHOP, COOKED_GROUND_BEEF, COOKED_GROUND_PORKCHOP, COOKED_GROUND_BEEF_PORKCHOP, LASAGNA_PASTA, CHEESE, LASAGNA_SLICE;
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}

    private static RegistryObject<Item> setItem(String name, FoodProperties food) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(BakedDelight.CREATIVE_TAB).food(food)));
    }
    static {
        // Item
        ROLLING_PIN = ITEMS.register("rolling_pin", () -> new SwordItem(Tiers.WOOD, 2, -2.4F, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
        // Food
        GROUND_BEEF = setItem("ground_beef", FoodValues.GROUND_BEEF);
        GROUND_PORKCHOP = setItem("ground_porkchop", FoodValues.GROUND_PORKCHOP);
        GROUND_BEEF_PORKCHOP = setItem("ground_beef_porkchop", FoodValues.GROUND_BEEF_PORKCHOP);
        COOKED_GROUND_BEEF = setItem("cooked_ground_beef", FoodValues.COOKED_GROUND_BEEF);
        COOKED_GROUND_PORKCHOP = setItem("cooked_ground_porkchop", FoodValues.COOKED_GROUND_PORKCHOP);
        COOKED_GROUND_BEEF_PORKCHOP = setItem("cooked_ground_beef_porkchop", FoodValues.COOKED_GROUND_BEEF_PORKCHOP);
        LASAGNA_PASTA = setItem("lasagna_pasta", FoodValues.LASAGNA_DOUGH);
        CHEESE = setItem("cheese", FoodValues.CHEESE);
        LASAGNA_SLICE = setItem("lasagna_slice", FoodValues.CHEESE);

    }
}
