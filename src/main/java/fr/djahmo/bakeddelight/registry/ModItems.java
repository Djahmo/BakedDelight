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
    public static final RegistryObject<Item> GROUND_MEAT, COOKED_GROUND_MEAT, LASAGNA_PASTA, CHEESE, LASAGNA_SLICE, BOLOGNESE;
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}

    private static RegistryObject<Item> setItem(String name, FoodProperties food) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(BakedDelight.CREATIVE_TAB).food(food)));
    }
    static {
        // Item
        ROLLING_PIN = ITEMS.register("rolling_pin", () -> new SwordItem(Tiers.WOOD, 2, -2.4F, new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
        // Food

        GROUND_MEAT = setItem("ground_meat", FoodValues.GROUND_MEAT);
        COOKED_GROUND_MEAT = setItem("cooked_ground_meat", FoodValues.COOKED_GROUND_MEAT);
        LASAGNA_PASTA = setItem("lasagna_pasta", FoodValues.LASAGNA_DOUGH);
        CHEESE = setItem("cheese", FoodValues.CHEESE);
        LASAGNA_SLICE = setItem("lasagna_slice", FoodValues.CHEESE);
        BOLOGNESE = setItem("bolognese", FoodValues.BOLOGNESE);

    }
}
