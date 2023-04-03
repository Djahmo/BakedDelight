package fr.djahmo.bakeddelight;

import fr.djahmo.bakeddelight.registry.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(BakedDelight.MODID)
public class BakedDelight {
    public static final String MODID = "bakeddelight";
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("bakeddelight") {
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.COOKED_LASAGNA_DISH.get());
        }
    };

    public BakedDelight() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntity.register(eventBus);
        ModSounds.register(eventBus);
        ModRecipe.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {}
}
