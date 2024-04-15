package net.djahmo.bakeddelight.registry;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.item.FoodItem;
import net.djahmo.bakeddelight.utils.FoodValues;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS;

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB;

    public static void register(IEventBus eventBus) { CREATIVE_TABS.register(eventBus); }

    static {
        CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BakedDelight.MODID);

        CREATIVE_TAB = CREATIVE_TABS.register("bakeddelight",
                () -> CreativeModeTab.builder()
                        .icon(() -> new ItemStack(ModBlocks.COOKED_LASAGNA_DISH.get()))
                        .title(Component.translatable("itemGroup.bakeddelight"))
                        .displayItems(((pParameters, pOutput) -> {

                            //Blocks
                            pOutput.accept(ModBlocks.BAKING_DISH.get());
                            pOutput.accept(ModBlocks.UNCOOKED_LASAGNA_DISH.get());
                            pOutput.accept(ModBlocks.COOKED_LASAGNA_DISH.get());
                            pOutput.accept(ModBlocks.UNCOOKED_GRATIN_DISH.get());
                            pOutput.accept(ModBlocks.COOKED_GRATIN_DISH.get());

                            //Items
                            pOutput.accept(ModItems.GROUND_MEAT.get());
                            pOutput.accept(ModItems.COOKED_GROUND_MEAT.get());
                            pOutput.accept(ModItems.LASAGNA_PASTA.get());
                            pOutput.accept(ModItems.SLICED_POTATO.get());
                            pOutput.accept(ModItems.CHEESE.get());
                            pOutput.accept(ModItems.BACON_BITS.get());
                            pOutput.accept(ModItems.COOKED_BACON_BITS.get());
                            pOutput.accept(ModItems.BOLOGNESE.get());
                            pOutput.accept(ModItems.BACON_CREAM.get());
                            pOutput.accept(ModItems.LASAGNA_SLICE.get());
                            pOutput.accept(ModItems.GRATIN_SLICE.get());

                            //Tools
                            pOutput.accept(ModItems.ROLLING_PIN.get());

                            //Suits
                            pOutput.accept(ModItems.CHEFS_HAT.get());
                            pOutput.accept(ModItems.CHEFS_JACKET.get());
                            pOutput.accept(ModItems.CHEFS_PANTS.get());
                            pOutput.accept(ModItems.CHEFS_SHOES.get());

                        }))
                        .build()
        );
    }
}
